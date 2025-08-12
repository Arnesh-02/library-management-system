package service;

import exception.BookNotFoundException;
import model.Admin;
import model.Book;
import model.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static model.Book.allBooks;

public class AdminService {
    public Scanner sc=new Scanner(System.in);
    public AdminService(){

    }

    public void listAdminServices(){
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
    }

    public void addAdmin(){

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

        Admin newAdmin=new Admin(name,phoneNo,address,email,password,dob,salary);
        System.out.println("New Admin added successfully..!");
    }
    public void addBook(){

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

        Book newBook=new Book(bName,authorName,ISBN,quantity,category,publication,edition);
        System.out.println("New book added successfully..!");
    }

    public void modifyBookDetails(String isbn){
        List<Book> allBooks= Book.allBooks;
        for(Book book:allBooks){
            if(book.getISBN().equals(isbn)){
                System.out.print("Enter updated book name (leave blank to keep '" + book.getbName() + "'): ");
                String input = sc.nextLine();
                if (!input.isBlank()) book.setbName(input);

                System.out.print("Enter updated author name (leave blank to keep '" + book.getAuthorName() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setAuthorName(input);

                System.out.print("Enter updated quantity (leave blank to keep '" + book.getQuantity() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setQuantity(Integer.parseInt(input));

                System.out.print("Enter updated category (leave blank to keep '" + book.getCategory() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setCategory(input);

                System.out.print("Enter updated publication (leave blank to keep '" + book.getPublication() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setPublication(input);

                System.out.print("Enter updated status (AVAILABLE, OUT_OF_STOCK, RESERVED) (leave blank to keep '" + book.getStatus() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setStatus( Status.valueOf(input.toUpperCase()));

                System.out.print("Enter updated edition (leave blank to keep '" + book.getEdition() + "'): ");
                input = sc.nextLine();
                if (!input.isBlank()) book.setEdition(Integer.parseInt(input));
            }
        }
    }

    public void deleteBook(){
        System.out.println("Enter the ISBN number of the book to be deleted");
        String isbn=sc.nextLine();

        for(Book i: allBooks){
            if(i.getISBN().equals(isbn)){
                allBooks.remove(i);
                System.out.println("Book deleted successfully..!");
                return;
            }
        }
        throw new BookNotFoundException();
    }
}
