package service.ui;

import model.BorrowedBooks;
import model.Borrower;
import model.Book;
import model.User;
import service.ReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static model.BorrowedBooks.allBorrowedBooks;


public class ReportServiceUi {
    private static final Scanner sc = new Scanner(System.in);
    private static final ReportService reportService = new ReportService();

    public static void showReports(String role, User user) {
        boolean running = true;

        do {
            System.out.println("\n===== Reports Menu (" + role + ") =====");
            if (role.equalsIgnoreCase("administrator")) {
                System.out.println("1. Show Low Stock Books");
                System.out.println("2. Show Never Borrowed Books");
                System.out.println("3. Show Heavily Borrowed Books");
                System.out.println("4. Show Book Status by ISBN");
                System.out.println("5. Show Outstanding Borrowers");
                System.out.println("6. Exit Reports Menu");
            } else if (role.equalsIgnoreCase("Borrower")) {
                System.out.println("1. Show My Fine History");
                System.out.println("2. Show My Borrow History");
                System.out.println("3. Exit Reports Menu");
            } else {
                System.out.println("Invalid Role! Only Admin or Borrower supported.");
                return;
            }

            System.out.print("Choose an option: ");
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            try {
                if (role.equalsIgnoreCase("administrator")) {
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter stock threshold: ");
                            int lowStock = sc.nextInt(); sc.nextLine();
                            List<Book> lowStockBooks = reportService.showLowStockBooks(lowStock);
                            if(lowStockBooks.isEmpty()){
                                System.out.println("No books are not under "+lowStock+" quantity..!");
                            }
                            lowStockBooks.forEach(System.out::println);
                        }
                        case 2 -> {
                            List<Book> neverBorrowed = reportService.showNeverBorrowedBooks();
                            if(neverBorrowed.isEmpty()){
                                System.out.println("All the books have been taken at least 1 times");
                                break;
                            }
                            neverBorrowed.forEach(System.out::println);
                        }
                        case 3 -> {
                            System.out.print("Enter borrow count threshold: ");
                            int count = sc.nextInt(); sc.nextLine();
                            List<Book> heavyBooks = reportService.showHeavilyBorrowedBooks(count);
                            if(heavyBooks.isEmpty()){
                                System.out.println("No books have been borrowed more than "+count+" times.");
                                break;
                            }
                            heavyBooks.forEach(System.out::println);
                        }
                        case 4 -> {
                            System.out.print("Enter ISBN: ");
                            String isbn = sc.nextLine();
                            reportService.showBookStatusByISBN(allBorrowedBooks, isbn);
                        }
                        case 5 -> {
                            System.out.print("Enter date (YYYY-MM-DD): ");
                            LocalDate givenDate = LocalDate.parse(sc.nextLine());
                            reportService.showOutstandingBorrowers(allBorrowedBooks, givenDate);
                        }
                        case 6 -> {
                            System.out.println("Exiting Reports Menu...");
                            running = false;
                        }
                        default -> System.out.println("Invalid choice, try again.");
                    }
                } else if (role.equalsIgnoreCase("Borrower")) {
                    Borrower borrower=(Borrower)user;
                    switch (choice) {
                        case 1 -> reportService.showStudentFineHistory(borrower);
                        case 2 -> reportService.showStudentBorrowHistory(borrower);
                        case 3 -> {
                            System.out.println("Exiting Reports Menu...");
                            running = false;
                        }
                        default -> System.out.println("Invalid choice, try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (running);
    }
}
