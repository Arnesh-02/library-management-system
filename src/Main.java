import exception.AdminNotFoundException;
import exception.UserNotFoundException;
import model.Admin;
import service.AuthService;
import service.ui.AdminServicesUI;

import java.time.LocalDate;
import java.util.Scanner;

import static model.Admin.adminList;
import static service.AuthService.allUsers;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
//    public static  List<Borrower> borrowerList = new ArrayList<>();

    public static String[] getEmailAndPassword(){
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return new String[]{email,password};
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Admin admin1 = new Admin();
        admin1.setName("Admin 1");
        admin1.setPhoneNo("848484848");
        LocalDate localDate = LocalDate.of(2004, 11, 21);
        admin1.setDob(localDate);
        admin1.setEmail("admin1@gmail.com");
        admin1.setPassword("123");
        admin1.setSalary(50000);

        adminList.add(admin1);
        allUsers.add(admin1);

        do{
            System.out.println(allUsers);
            System.out.println("\n===== LIBRARY SYSTEM MENU =====");
            System.out.println("1. Login");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        String[] details =getEmailAndPassword();
                        authService.login(details[0], details[1]);
                        AdminServicesUI.chooseAdminServices();

                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        continue;
                    }

                case 2:
                    authService.logout();
                    break;
                case 3:
                    System.out.println("Exiting... Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }while (true);
    }
}
