package service;


import exception.BookNotFoundException;
import model.Book;
import model.Borrower;

import static model.Book.allBooks;



public class BorrowerService {


    public void getAllBooks(){
        allBooks.forEach(i-> System.out.println(i));
    }

    // Add to cart: enforce quantity, no duplicates, max 3
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
            borrower.getBorrowedBooks().add(book);
            book.setQuantity(book.getQuantity() - 1);
        }
        borrower.getCheckoutCart().clear();
        System.out.println("Checkout complete.");
    }


    public void deleteFromCart(Borrower borrower,Book book){
        for(Book j:borrower.getCheckoutCart()){
            if(j.equals(book)){
                borrower.getCheckoutCart().remove(j);
                System.out.println("Item deleted Successfully..!");
                return;
            }
        }
        System.out.println("No such book exits in your cart..Enter the details correctly..!");
    }



}
