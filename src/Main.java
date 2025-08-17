import exception.AdminNotFoundException;
import exception.UserNotFoundException;
import model.Admin;
import model.Borrower;
import model.Roles;
import model.User;
import service.AuthService;
import service.ui.AdminServicesUI;
import service.ui.BorrowerServiceUi;

import java.time.LocalDate;
import java.util.Scanner;

import static model.Admin.adminList;
import static service.AuthService.allUsers;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
//    public static  List<Borrower> borrowerList = new ArrayList<>();

    public static String[] getEmailAndPassword(){
        allUsers.forEach(System.out::println);
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return new String[]{email,password};
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Admin admin1=new Admin("Arnesh","1232412","aasnfuasf","a@g.com","123",LocalDate.now(),5000);

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
                        User user = authService.login(details[0], details[1]);
                        System.out.println(user.getRole());

                        if (user.getRole().equals(Roles.administrator)) {
                            AdminServicesUI.chooseAdminServices();
                        } else if (user.getRole().equals(Roles.borrower)) {
                            BorrowerServiceUi.chooseBorrowerServices((Borrower) user);
                        }


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
