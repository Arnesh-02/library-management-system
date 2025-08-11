package model;

public class Admin extends User {
    private int adminId;
    private int salary;
    private Roles role=Roles.administrator;

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
