package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    public static List<Admin> adminList = new ArrayList<>();
    private static int  adminIdCounter=100;
    private int adminId;
    private int salary;
    private Roles role;


    public Admin(String name, String phoneNo, String address, String email, String password,
                 LocalDate dob,int salary) {
        super(name,phoneNo,address,email,password,dob);
        this.adminId = ++adminIdCounter;
        this.salary = salary;
        this.role = Roles.administrator;
        adminList.add(this);
    }

    public Admin() {
        super();
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", salary=" + salary +
                ", role=" + role +
                '}';
    }
}
