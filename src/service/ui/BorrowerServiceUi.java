package service.ui;


import model.Book;
import model.Borrower;
import service.BorrowerService;

import java.util.Scanner;

import static model.Book.allBooks;

public class BorrowerServiceUi {
    private static final Scanner sc = new Scanner(System.in);
    private static final BorrowerService borrowerService = new BorrowerService();

    public static void chooseBorrowerServices(Borrower borrower) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Borrower Menu =====");
            System.out.println("1. View All Books");
            System.out.println("2. View Cart");
            System.out.println("3. Add Book to Cart");
            System.out.println("4. Remove Book from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Borrowed Books");
            System.out.println("7. Exit Borrower Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> borrowerService.getAllBooks();

                case 2 -> {
                    if (borrower.getCheckoutCart().isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Your Cart:");
                        borrower.getCheckoutCart().forEach(System.out::println);
                    }
                }

                case 3 -> {
                    System.out.print("Enter Book ISBN or Name: ");
                    String input = sc.nextLine();

                    Book bookToAdd = allBooks.stream()
                            .filter(b -> b.getISBN().equalsIgnoreCase(input) || b.getbName().equalsIgnoreCase(input))
                            .findFirst()
                            .orElse(null);

                    if (bookToAdd != null) {
                        borrowerService.addToCart(borrower, bookToAdd);
                    } else {
                        System.out.println("Book not found.");
                    }
                }

                case 4 -> {
                    System.out.print("Enter Book ISBN or Name to remove: ");
                    String input = sc.nextLine();

                    Book bookToRemove = borrower.getCheckoutCart().stream()
                            .filter(b -> b.getISBN().equalsIgnoreCase(input) || b.getbName().equalsIgnoreCase(input))
                            .findFirst()
                            .orElse(null);

                    if (bookToRemove != null) {
                        borrowerService.deleteFromCart(borrower, bookToRemove);
                    } else {
                        System.out.println("Book not found in cart.");
                    }
                }

                case 5 -> borrowerService.checkout(borrower);

                case 6 -> {
                    if (borrower.getBorrowedBooks().isEmpty()) {
                        System.out.println("You haven’t borrowed any books yet.");
                    } else {
                        System.out.println("Your Borrowed Books:");
                        borrower.getBorrowedBooks().forEach(System.out::println);
                    }
                }

                case 7 -> {
                    System.out.println("Exiting Borrower Menu...");
                    running = false;
                }

                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

