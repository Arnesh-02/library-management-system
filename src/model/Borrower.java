package model;

import java.util.List;

public class Borrower extends User{
    private  int userId;
    private int depositBalance;
    private Roles role=Roles.borrower;
    private List<Book> borrowedBooks;

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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
