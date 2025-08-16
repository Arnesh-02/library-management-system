package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Borrower extends User{
    private  int userId;
    private int depositBalance;
    private List<Book> borrowedBooks;
    private static int userIdCounter=1;
    private static final List<Borrower> allBorrowers=new ArrayList<>();

    public Borrower(String name, String phoneNo, String address, String email, String password, LocalDate dob) {
        super(name, phoneNo, address, email, password, dob,Roles.borrower);
        this.userId=userIdCounter++;
        this.depositBalance = 1500;
        this.borrowedBooks = new ArrayList<Book>();
        allBorrowers.add(this);
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


    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
