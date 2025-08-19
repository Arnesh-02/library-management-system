import exception.AdminNotFoundException;
import exception.UserNotFoundException;
import model.*;
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

        Borrower b1 = new Borrower("Arjun", "9876543210", "Chennai", "a", "1", LocalDate.of(2000, 5, 12));
        Borrower b2 = new Borrower("Meera", "9123456780", "Bangalore", "meera@exampe.com", "meera@123", LocalDate.of(1999, 8, 25));
        Borrower b3 = new Borrower("Rahul", "9988776655", "Hyderabad", "rahul@example.com", "rahul@2024", LocalDate.of(2001, 3, 3));

        // Hardcoded Books
        Book book1 = new Book("The Alchemist", "Paulo Coelho", "ISBN12345", 10, "Fiction", "HarperCollins", 3, 399);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "ISBN54321", 5, "Programming", "Prentice Hall", 1, 550);
        Book book3 = new Book("Introduction to Algorithms", "Thomas H. Cormen", "ISBN98765", 7, "Education", "MIT Press", 4, 1250);
        Book book4 = new Book("Wings of Fire", "A.P.J. Abdul Kalam", "ISBN67890", 8, "Autobiography", "Universities Press", 2, 299);

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
