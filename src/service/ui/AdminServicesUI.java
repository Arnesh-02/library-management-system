package service.ui;

import exception.BookNotFoundException;
import model.Book;
import model.Borrower;
import service.AdminService;
import service.AuthService;

import java.time.LocalDate;
import java.util.Scanner;

import static model.Book.allBooks;

public class AdminServicesUI {
    private static final Scanner sc = new Scanner(System.in);
    private static final AdminService service = new AdminService();
    private static final AuthService authService = new AuthService();
    public static void chooseAdminServices(){
        do {
            System.out.println("\n--- Admin Services ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Modify Book details (e.g., Available Quantity)");
            System.out.println("3. Delete a Book");
            System.out.println("4. Add other Admins");
            System.out.println("5. Add Borrowers");
            System.out.println("6. View list of all Books sorted by:");
            System.out.println("   a) Name");
            System.out.println("   b) Available Quantity");
            System.out.println("7. Search for a Book by:");
            System.out.println("   a) Name");
            System.out.println("   b) ISBN");
            System.out.println("8. Manage Borrowers' fine limit");
            System.out.println("9. Logout");
            System.out.println("Enter your choice : ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter book name: ");
                    String bName = sc.nextLine();

                    System.out.print("Enter author name: ");
                    String authorName = sc.nextLine();

                    System.out.print("Enter ISBN: ");
                    String ISBN = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter category(Programming,Comics..): ");
                    String category = sc.nextLine();

                    System.out.print("Enter publication(Sura,Doms..): ");
                    String publication = sc.nextLine();

                    System.out.print("Enter edition(2011,2025): ");
                    int edition = sc.nextInt();

                    System.out.print("Enter the Retail price of the Book: ");
                    int price=sc.nextInt();
                    sc.nextLine();
                    service.addBook(bName, authorName, ISBN, quantity, category, publication, edition,price);
                    break;

                case 2:
                    try {
                        System.out.println("Enter the isbn of the book to change the details:");
                        String isbn = sc.nextLine();
                        service.modifyBookDetails(isbn);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                case 3:
                    try {
                        System.out.println("Enter the ISBN number of the book to be deleted");
                        String Isbn = sc.nextLine();
                        service.deleteBook(Isbn);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                case 4:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phoneNo = sc.nextLine();

                    System.out.print("Enter address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    System.out.print("Enter DOB (YYYY-MM-DD): ");
                    LocalDate dob = LocalDate.parse(sc.nextLine());


                    System.out.print("Enter salary: ");
                    int salary = Integer.parseInt(sc.nextLine());


                    service.addAdmin(name, phoneNo, address, email, password, dob, salary);
                    break;
                case 5:
                    // Add borrower

                    System.out.print("Enter name: ");
                    String borName = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String borPhoneNo = sc.nextLine();

                    System.out.print("Enter address: ");
                    String borAddress = sc.nextLine();

                    System.out.print("Enter email: ");
                    String borEmail = sc.nextLine();

                    System.out.print("Enter password: ");
                    String borPassword = sc.nextLine();

                    System.out.print("Enter DOB (YYYY-MM-DD): ");
                    LocalDate borDob = LocalDate.parse(sc.nextLine());

                    Borrower borrower=new Borrower(borName,borPhoneNo,borAddress,borEmail,borPassword,borDob);
                    System.out.println("Borrower account has been added successfully!");
                    break;

                case 6:
                    System.out.println("Enter the choice:\n1.Sort by Name\n2.Sort by quantity");
                    int sch = sc.nextInt();
                    sc.nextLine();
                    switch (sch) {
                        case 1 -> service.sortByName();
                        case 2 -> service.sortByQuantity();
                        default -> {
                            System.out.println("Invalid choice");
                        }
                    }
                    for (Book i : allBooks) {
                        System.out.println(i);
                    }
                    break;
                case 7:
                    //search for books
                    System.out.println("Enter option to search book\n1.By name\n2.By ISBN");
                    int op = sc.nextInt();
                    sc.nextLine();
                    try {
                        switch (op) {
                            case 1 -> {
                                System.out.println("Enter name of the book");
                                String book = sc.nextLine().trim();
                                System.out.println(service.searchBookByName(book));
                            }
                            case 2 -> {
                                System.out.println("Enter the ISBN Number");
                                String isbn = sc.nextLine().trim();
                                System.out.println(service.searchBookByISBN(isbn));
                            }
                            default -> System.out.println("Invalid choice");
                        }
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                case 8:
                    //borrower limit change
                case 9:
                     authService.logout();
                     return;
                default:
                    System.out.println("Invalid choice");

            }
        } while (true);

    }
}
