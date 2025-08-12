import exception.AdminNotFoundException;
import model.Admin;
import model.Borrower;
import model.User;
import service.AuthService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Admin.adminList;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
//    public static  List<Borrower> borrowerList = new ArrayList<>();
    private  static User currLoggedIn=null;
    private static boolean overallLoggedInStatus;

    public static String[] getEmailAndPassword(){
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return new String[]{email,password};
    }

    public static void doLoginMessagePrinter(){
        System.out.println("Do login first to interact with the system");
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Admin admin1 = new Admin();
        admin1.setName("Admin 1");
        admin1.setPhoneNo("848484848");
        LocalDate localDate = LocalDate.of(2004, 11, 21);
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
            System.out.println("1. Add Admin");
            System.out.println("2. Add Borrower");
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
                    try {
                        System.out.print("Are you logging in as ADMIN or BORROWER? ");
                        String role = sc.nextLine().toUpperCase();

                        if (role.equals("ADMIN")) {
                            String[] details =getEmailAndPassword();
                            currLoggedIn=authService.adminLogin(details[0], details[1]);
                            overallLoggedInStatus=true;
                        }
//                        else if (role.equals("BORROWER")) {
//                            String[] details =getEmailAndPassword();
//                            currLoggedIn=authService.borrowerLogin(details[0], details[1], borrowerList);
//                            overallLoggedInStatus=true;
//                        }
                        else {
                            System.out.println("Invalid role.");
                            return;
                        }
                    } catch (AdminNotFoundException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        continue;
                    }

                case 4:
                    if(overallLoggedInStatus){
                        authService.logout(currLoggedIn);
                    }
                    else{
                       doLoginMessagePrinter();
                    }
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
}
