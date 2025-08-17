package model;

import java.time.LocalDate;
import java.util.*;

public class Borrower extends User{
    private  int userId;
    private int depositBalance;
    private Set<Book> borrowedBooks;
    private static int userIdCounter=1;
    private Set<Book> checkoutCart=new HashSet<>();

    private static final List<Borrower> allBorrowers=new ArrayList<>();

    public Borrower(String name, String phoneNo, String address, String email, String password, LocalDate dob) {
        super(name, phoneNo, address, email, password, dob,Roles.borrower);
        this.userId=userIdCounter++;
        this.depositBalance = 1500;
        this.borrowedBooks = new HashSet<>();
        allBorrowers.add(this);
    }

    public Set<Book> getCheckoutCart() {
        return checkoutCart;
    }

    public void setCheckoutCart(Set<Book> checkoutCart) {
        this.checkoutCart = checkoutCart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(int depositBalance) {
        this.depositBalance = depositBalance;
    }


    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
