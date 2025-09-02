package service;

import model.Book;
import model.BookStatus;
import model.BorrowedBooks;
import model.Borrower;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.Book.allBooks;

public class ReportService {
    public List<Book> showLowStockBooks(int lowStock){
        List<Book> lowStockBooks =new ArrayList<>();
        for(Book book: allBooks){
            if(book.getQuantity()<lowStock){
                lowStockBooks.add(book);
            }
        }
        return lowStockBooks;
    }

    public List<Book> showNeverBorrowedBooks(){
        List<Book> neverBorrowedBooks= new ArrayList<>();
        for(Book book: allBooks){
            if(book.getBorrowCount()==0){
                neverBorrowedBooks.add(book);
            }
        }
        return neverBorrowedBooks;
    }

    public List<Book> showHeavilyBorrowedBooks(int borrowCount){
        List<Book> heavilyBorrowedBooks=new ArrayList<>();
        for(Book book:allBooks){
            if(book.getBorrowCount()>borrowCount){
                heavilyBorrowedBooks.add(book);
            }
        }
        return heavilyBorrowedBooks;
    }


    public void showBookStatusByISBN(List<BorrowedBooks> borrowedBooks, String isbn) {
        System.out.println("===== Book Status for ISBN: " + isbn + " =====");
        borrowedBooks.stream()
                .filter(b -> b.getBook().getISBN().equalsIgnoreCase(isbn))
                .forEach(b -> System.out.println(
                        "Book: " + b.getBook().getbName() +
                                " | Borrower: " + b.getBorrower().getName() +
                                " | Status: " + b.getBookStatus() +
                                " | Borrowed: " + b.getBorrowedDate() +
                                " | Due: " + b.getDueDate() +
                                " | Returned: " + (b.getReturnDate() != null ? b.getReturnDate() : "Not returned") +
                                " | Fine: " + b.getFine()
                ));
    }


    public void showOutstandingBorrowers(List<BorrowedBooks> borrowedBooks, LocalDate givenDate) {
        System.out.println("===== Outstanding Borrowers as of " + givenDate + " =====");

        borrowedBooks.stream()
                .filter(b -> b.getBookStatus() == BookStatus.taken)
                .filter(b -> b.getDueDate().isBefore(givenDate))
                .forEach(b -> System.out.println(
                        "Borrower: " + b.getBorrower().getName() +
                                " | Book: " + b.getBook().getbName() +
                                " | Status: " + b.getBookStatus() +
                                " | Due: " + b.getDueDate()
                ));
    }



    public void showStudentFineHistory(Borrower borrower) {
        System.out.println("===== Fine History for " + borrower.getName() + " =====");
        borrower.getBorrowedBooks().stream()
                .filter(b -> b.getFine() > 0)
                .forEach(b -> System.out.println(
                        "Book: " + b.getBook().getbName() +
                                " | Status: " + b.getBookStatus() +
                                " | Fine: " + b.getFine()
                ));
    }



    public void showStudentBorrowHistory(Borrower borrower) {
        System.out.println("===== Borrow History for " + borrower.getName() + " =====");
        borrower.getBorrowedBooks().forEach(b -> System.out.println(
                "Book: " + b.getBook().getbName() +
                        " | Borrowed: " + b.getBorrowedDate() +
                        " | Due: " + b.getDueDate() +
                        " | Returned: " + (b.getReturnDate() != null ? b.getReturnDate() : "Not returned") +
                        " | Status: " + b.getBookStatus() +
                        " | Fine: " + b.getFine()
        ));
    }

}
