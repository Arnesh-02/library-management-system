package service;


import exception.BookNotFoundException;
import model.Book;
import model.BookStatus;
import model.BorrowedBooks;
import model.Borrower;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static model.Book.allBooks;



public class BorrowerService {
    private final Scanner sc=new Scanner(System.in);

    public void getDepositBalance(Borrower borrower){
        System.out.println("Your remaining deposit balance is "+borrower.getDepositBalance());
    }

    private BorrowedBooks findBorrowedBook(Borrower borrower, Book book) {
        return borrower.getBorrowedBooks().stream()
                .filter(b -> b.getBook().equals(book))
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }


    private BorrowedBooks findBorrowedBookByISBN(Borrower borrower, String isbn) {
        return borrower.getBorrowedBooks().stream()
                .filter(b -> b.getBook().getISBN().equalsIgnoreCase(isbn))
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }


    public void getAllBooks(){
        allBooks.forEach(System.out::println);
    }

    public void getAllCheckoutCartBooks(Borrower borrower){
        borrower.getCheckoutCart().forEach(System.out::println);
    }


    public void addToCart(Borrower borrower, Book book) {
        if (borrower.getCheckoutCart().size() + borrower.getBorrowedBooks().size() >= 3) {
            System.out.println("Limit of 3 books reached.");
            return;
        }
        if (borrower.getCheckoutCart().contains(book)) {
            System.out.println("Book already in cart.");
            return;
        }
        if (book.getQuantity() <= 0) {
            System.out.println("Book not available.");
            return;
        }
        borrower.getCheckoutCart().add(book);
        System.out.println(book.getbName() + " added to cart.");
    }

    public void checkout(Borrower borrower) {
        if (borrower.getDepositBalance() < 500) {
            System.out.println("Deposit too low. Cannot borrow.");
            return;
        }
        for (Book book : borrower.getCheckoutCart()) {
            borrower.getBorrowedBooks().add(new BorrowedBooks(book,LocalDate.now(),LocalDate.now().plusDays(15)));
            book.setQuantity(book.getQuantity() - 1);
        }
        borrower.getCheckoutCart().clear();
        System.out.println("Checkout complete.");

    }


    public void deleteFromCart(Borrower borrower,Book book){
        if(borrower.getCheckoutCart().isEmpty()){
            System.out.println("Checkout cart is empty..!");
        }
        for(Book j:borrower.getCheckoutCart()){
            if(j.equals(book)){
                borrower.getCheckoutCart().remove(j);
                j.setQuantity(j.getQuantity()+1);
                System.out.println("Item deleted Successfully..!");
                return;
            }
        }
        System.out.println("No such book exits in your cart..Enter the details correctly..!");
    }


    public void viewBorrowedBooks(Borrower borrower){
        borrower.getBorrowedBooks().forEach(System.out::println);
    }

    public void extendTenure(Borrower borrower,String isbn){
        BorrowedBooks borrowedBook =findBorrowedBookByISBN(borrower,isbn);
        if(borrowedBook.getExtensionsMade()<2)  {
            borrowedBook.setExtensionsMade(borrowedBook.getExtensionsMade()+1);
            borrowedBook.setDueDate(borrowedBook.getDueDate().plusDays(15));
            System.out.println("The tenure for returning " + borrowedBook.getBook().getbName() +"+ is extended for another 15 days");
        }
        else{
            System.out.println("You have already reached the limit of extending due date");
        }
    }

    public  double calculateBookLostFine(Book book){
        return book.getPrice()*0.50;
    }

    public double calculateMemberCardLostFine(){
        return 10.0;
    }



    public void reportLostBook(Borrower borrower,Book book){
        BorrowedBooks borrowedBook = findBorrowedBook(borrower, book);
        borrowedBook.setFine(calculateBookLostFine(borrowedBook.getBook()));
        borrowedBook.setBookStatus(BookStatus.lost);
        System.out.println("The book has been reported as lost. " +
                "As per library policy, you are required to pay the replacement cost or the applicable fine. " +
                "Kindly settle this.\n");
        payFine(borrower,borrowedBook);
        borrower.getBorrowedBooks().remove(borrowedBook);
    }

    public void reportMemberShipCardLost(Borrower borrower){
        System.out.println("Membership card is now blocked temporarily");
        double fine=calculateMemberCardLostFine();
        payFine(borrower,fine);
    }

    public void payFine(Borrower borrower,double fine){
        if(fine<=0){
            System.out.println("No fine..! Good to go..");
            return;
        }

        System.out.println("Your calculated amount of fine is "+fine);
        System.out.println("Do you want to pay by Deposit or not ? (yes/no)");
        String ch = sc.next();
        if (ch.equalsIgnoreCase("Yes")) {
            if (borrower.getDepositBalance() < fine) {
                System.out.println("Balance insufficient to make this transaction..!");
                System.out.println("Amount to be paid Rs. " + fine);
                System.out.println("Pay the fine to the counter");
            }
            else{
                borrower.setDepositBalance(borrower.getDepositBalance() - fine);
                System.out.println("Amount of Rs. " + fine + " has been detected from your deposit.!");
            }
        } else {
            System.out.println("Amount to be paid Rs. " + fine);
            System.out.println("Pay the fine to the counter");
        }
    }

    public void returnABook(Borrower borrower, Book bookToReturn, LocalDate returnDate) {
        BorrowedBooks borrowedBook = findBorrowedBook(borrower, bookToReturn);
        double fine = calculateBookFine(borrower, bookToReturn, returnDate);
        borrowedBook.setBookStatus(BookStatus.returned);
        borrowedBook.setFine(fine);
        payFine(borrower, borrowedBook);
        bookToReturn.setQuantity(bookToReturn.getQuantity()+1);
        System.out.println("Book returned successfully..!");
    }



    private double calculateBookFine(Borrower borrower, Book bookToReturn, LocalDate returnDate) {
        double fine=0;
        double rate=2;
        BorrowedBooks borrowedBook = findBorrowedBook(borrower, bookToReturn);
        int overDueDays=(int) ChronoUnit.DAYS.between(borrowedBook.getDueDate(),returnDate);
        System.out.println("Number of overDue : "+overDueDays);
        while(overDueDays>0){
            int block=Math.min(10,overDueDays);
            fine+=block*rate;
            overDueDays-=block;
            rate*=2;
        }
       return fine;
    }

    public void payFine(Borrower borrower,BorrowedBooks borrowedBook) {
        double fine = borrowedBook.getFine();
        if (fine <=0) {
            System.out.println("No fine to pay.! Good to go");
            return;
        }
        System.out.println("Your calculated amount of fine is "+fine);
        System.out.println("Do you want to pay by Deposit or not ? (yes/no)");
        String ch = sc.next();
        if (ch.equalsIgnoreCase("Yes")) {
            if (borrower.getDepositBalance() < fine) {
                System.out.println("Balance insufficient to make this transaction..!");
                System.out.println("Amount to be paid Rs. " + fine);
                System.out.println("Pay the fine to the counter");

            }
            else{
                borrower.setDepositBalance(borrower.getDepositBalance() - fine);
                System.out.println("Amount of Rs. " + fine + " has been detected from your deposit.!");
            }
        } else {
            System.out.println("Amount to be paid Rs. " + fine);
            System.out.println("Pay the fine to the counter");
        }
        borrower.getBorrowedBooks().remove(borrowedBook);
    }

}
