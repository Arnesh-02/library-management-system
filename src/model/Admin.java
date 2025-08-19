package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Admin extends User {
    public static List<Admin> adminList = new ArrayList<>();
    private static int  adminIdCounter=100;
    private int adminId;
    private int salary;



    public Admin(String name, String phoneNo, String address, String email, String password,
                 LocalDate dob,int salary) {
        super(name,phoneNo,address,email,password,dob,Roles.administrator);
        this.adminId = ++adminIdCounter;
        this.salary = salary;
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

}
