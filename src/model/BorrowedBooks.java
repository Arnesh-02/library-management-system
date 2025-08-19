package model;

import java.time.LocalDate;

public class BorrowedBooks{
    private Book book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private int extensionsMade;
    private BookStatus bookStatus;

    public BorrowedBooks(Book book, LocalDate borrowedDate, LocalDate dueDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fine = 0;
        this.extensionsMade=0;
        this.bookStatus=BookStatus.taken;
    }


    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getExtensionsMade() {
        return extensionsMade;
    }

    public void setExtensionsMade(int extensionsMade) {
        this.extensionsMade = extensionsMade;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "BorrowedBooks{" +
                "book=" + book +
                ", borrowedDate=" + borrowedDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fine=" + fine +
                ", extensionsMade=" + extensionsMade +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
