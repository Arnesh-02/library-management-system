package service.ui;

import exception.BookNotFoundException;
import model.Book;
import service.AdminService;
import service.AuthService;

import java.time.LocalDate;
import java.util.Scanner;

import static model.Book.allBooks;

public class AdminServicesUI {
    private  Scanner sc=new Scanner(System.in);
    AdminService service=new AdminService();
    AuthService authService=new AuthService();
    public void chooseAdminServices(){
        while (true) {
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
            int ch=sc.nextInt();
            sc.nextLine();
            switch (ch){
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
                    sc.nextLine();
                    service.addBook(bName,authorName,ISBN,quantity,category,publication,edition);
                    break;

                case 2:
                    try {
                        System.out.println("Enter the isbn of the book to change the details:");
                        String isbn=sc.nextLine();
                        service.modifyBookDetails(isbn);
                        break;
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        continue;
                    }
                case 3:
                    try {
                        System.out.println("Enter the ISBN number of the book to be deleted");
                        String Isbn=sc.nextLine();
                        service.deleteBook(Isbn);
                        break;
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        continue;
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

                    service.addAdmin(name,phoneNo,address,email,password,dob,salary);
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Enter the choice:\n1.Sort by Name\n2.Sort by quantity");
                    char sch=sc.next().charAt(0);
                    switch (sch){
                        case 'a'->service.sortByName();
                        case 'b'->service.sortByQuantity();
                        default ->{
                            System.out.println("Invalid choice");
                        }
                    }
                    for(Book i:allBooks){
                        System.out.println(i);
                    }
                    break;
                case 7:
                    //search for books
                case 8:
                    //borrower limit change
                case 9:
//                    authService.logout();
                default:
                    System.out.println("Invalid choice");
                    break;

            }
        }

    }
}
