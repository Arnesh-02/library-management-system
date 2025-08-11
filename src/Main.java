import model.Admin;
import model.Borrower;
import service.AuthService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Admin> adminList = new ArrayList<>();
    private static final List<Borrower> borrowerList = new ArrayList<>();


    public static void main(String[] args) {
        AuthService authService=new AuthService();

        Admin admin1 = new Admin();
        admin1.setPhoneNo("848484848");
        LocalDate localDate=LocalDate.of(2004,11,21);
        admin1.setDob(localDate);
        admin1.setAdminId(144);
        admin1.setAdminId(1);
        admin1.setEmail("admin1@gmail.com");
        admin1.setPassword("123");
        admin1.setSalary(50000);

        adminList.add(admin1);
        boolean running = true;

        while (running) {
            System.out.println("\n===== LIBRARY SYSTEM MENU =====");
//            System.out.println("1. Add Admin");
//            System.out.println("2. Add Borrower");
            System.out.println("3. Login");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
//                case 1:
//                    addAdmin();
//                    break;
//                case 2:
//                    addBorrower();
//                    break;
                case 3:
                    System.out.print("Are you logging in as ADMIN or BORROWER? ");
                    String role = sc.nextLine().toUpperCase();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    if (role.equals("ADMIN")) {
                        authService.adminLogin(email,password,adminList);
                     }
                     else if (role.equals("BORROWER")) {
                        authService.borrowerLogin(email,password,borrowerList);
                     }
                    else {
                        System.out.println("Invalid role.");
                        return;
                    }

                case 4:
                    logout();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Bye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
