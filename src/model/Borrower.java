package model;

import java.time.LocalDate;
import java.util.*;

public class Borrower extends User{
    private  int userId;
    private double depositBalance;
    private static int userIdCounter=1;
    private Set<Book> checkoutCart=new HashSet<>();
    private  double outStandingFine;

    private List<BorrowedBooks> borrowedBooks;

    private static final List<Borrower> allBorrowers=new ArrayList<>();
    public Borrower(String name, String phoneNo, String address, String email, String password, LocalDate dob) {
        super(name, phoneNo, address, email, password, dob,Roles.borrower);
        this.userId=userIdCounter++;
        this.depositBalance = 1500;
        this.borrowedBooks = new ArrayList<>();
        this.outStandingFine=0;
        allBorrowers.add(this);
    }

    public Set<Book> getCheckoutCart() {
        return checkoutCart;
    }

    public double getOutStandingFine() {
        return outStandingFine;
    }

    public void setOutStandingFine(double outStandingFine) {
        this.outStandingFine = outStandingFine;
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

    public double getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance) {
        this.depositBalance = depositBalance;
    }

    public List<BorrowedBooks> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBooks> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}

// have to implement the borrowerService functions like payFine and calculate fine amt