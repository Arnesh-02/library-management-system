package service.ui;

import model.Book;
import model.BorrowedBooks;
import model.Borrower;
import service.BorrowerService;

import java.time.LocalDate;
import java.util.Scanner;

import static model.Book.allBooks;

public class BorrowerServiceUi {
    private static final Scanner sc = new Scanner(System.in);
    private static final BorrowerService borrowerService = new BorrowerService();

    public static void chooseBorrowerServices(Borrower borrower) {
        boolean running = true;

        do {
            System.out.println("1. View All Books");
            System.out.println("2. View Cart");
            System.out.println("3. Add Book to Cart");
            System.out.println("4. Remove Book from Cart");
            System.out.println("5. View Checkout Cart Books");
            System.out.println("6. Checkout");
            System.out.println("7. View Borrowed Books");
            System.out.println("8. Report lost book");
            System.out.println("9. Return a book");
            System.out.println("10. Report lost membership card");
            System.out.println("11. Extend the tenure of borrow");
            System.out.println("12. Exchange the book");
            System.out.println("13. View deposit balance");
            System.out.println("14. Exit Borrower Menu");

            System.out.print("Choose an option: ");
            int choice;
            try {
                String input = sc.nextLine();   // read full line
                choice = Integer.parseInt(input);  // try converting to number
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 13.");
                continue;  // restart loop, don’t fall through
            }
            try {
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
                        if (borrower.getCheckoutCart().isEmpty()) {
                            System.out.println("Cart is empty.");
                            break;
                        }

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

                    case 5 ->borrowerService.getAllCheckoutCartBooks(borrower);

                    case 6 -> borrowerService.checkout(borrower);

                    case 7 ->{
                        if(borrower.getBorrowedBooks().isEmpty())
                            System.out.println("There are no borrowed books..!");
                        borrowerService.viewBorrowedBooks(borrower);
                    }

                    case 8 -> {
                        if(borrower.getBorrowedBooks().isEmpty()){
                            System.out.println("There are no borrowed books..!");
                            break;
                        }
                        System.out.print("Enter ISBN or Book Name to report as lost: ");
                        String input = sc.nextLine();

                        Book lostBook = borrower.getBorrowedBooks().stream()
                                .map(BorrowedBooks::getBook)
                                .filter(b -> b.getISBN().equalsIgnoreCase(input) || b.getbName().equalsIgnoreCase(input))
                                .findFirst()
                                .orElse(null);

                        if (lostBook != null) {
                            borrowerService.reportLostBook(borrower, lostBook);
                        } else {
                            System.out.println("Book not found in your borrowed list.");
                        }
                    }

                    case 9 -> {
                        if(borrower.getBorrowedBooks().isEmpty()){
                            System.out.println("There are no borrowed books..!");
                            break;
                        }

                        System.out.print("Enter ISBN or Book Name to return: ");
                        String input = sc.nextLine();

                        Book returnBook = borrower.getBorrowedBooks().stream()
                                .map(BorrowedBooks::getBook)
                                .filter(b -> b.getISBN().equalsIgnoreCase(input) || b.getbName().equalsIgnoreCase(input))
                                .findFirst()
                                .orElse(null);

                        if (returnBook != null) {
                            System.out.print("Enter return date (YYYY-MM-DD): ");
                            LocalDate returnDate = LocalDate.parse(sc.nextLine());
                            borrowerService.returnABook(borrower, returnBook, returnDate);
                        } else {
                            System.out.println("Book not found in your borrowed list.");
                        }
                    }

                    case 10 -> borrowerService.reportMemberShipCardLost(borrower);

                    case 11 -> {
                        if(borrower.getBorrowedBooks().isEmpty()){
                            System.out.println("There are no borrowed books..!");
                            break;
                        }
                        System.out.print("Enter ISBN of the book to extend tenure: ");
                        String isbn = sc.nextLine();
                        borrowerService.extendTenure(borrower, isbn);
                    }

                    case 12 -> {
                        if(borrower.getBorrowedBooks().isEmpty()){
                            System.out.println("There are no borrowed books..!");
                            break;
                        }
                        System.out.print("Enter ISBN or Book Name of the book you want to exchange: ");
                        String input = sc.nextLine();

                        Book bookToExchange = borrower.getBorrowedBooks().stream()
                                .map(BorrowedBooks::getBook)
                                .filter(b -> b.getISBN().equalsIgnoreCase(input) || b.getbName().equalsIgnoreCase(input))
                                .findFirst()
                                .orElse(null);

                        if (bookToExchange != null) {
                            System.out.print("Enter ISBN or Name of the new book: ");
                            String newInput = sc.nextLine();

                            Book newBook = allBooks.stream()
                                    .filter(b -> b.getISBN().equalsIgnoreCase(newInput) || b.getbName().equalsIgnoreCase(newInput))
                                    .findFirst()
                                    .orElse(null);

                            if (newBook != null && newBook.getQuantity() > 0) {
                                borrowerService.returnABook(borrower, bookToExchange, LocalDate.now());
                                borrowerService.addToCart(borrower, newBook);
                                borrowerService.checkout(borrower);
                                System.out.println("Book exchanged successfully!");
                            } else {
                                System.out.println("Requested new book is not available.");
                            }
                        } else {
                            System.out.println("Book to exchange not found in your borrowed list.");
                        }
                    }

                    case 13 ->borrowerService.getDepositBalance(borrower);

                    case 14 -> {
                        System.out.println("Exiting Borrower Menu...");
                        running = false;
                    }

                    default -> System.out.println("Invalid choice, please try again.");
                }
            }
            catch (Exception f){
                System.out.println(f.getMessage());
            }

        }while (running);
    }
}
