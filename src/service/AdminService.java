package service;

import exception.BookNotFoundException;
import model.Admin;
import model.Book;
import model.Status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static model.Book.allBooks;

public class AdminService {
    public Scanner sc=new Scanner(System.in);
    public AdminService(){
    }


    public void addAdmin(String name,String phoneNo,String address,String email,String password,LocalDate dob,int salary){
        Admin newAdmin=new Admin(name,phoneNo,address,email,password,dob,salary);
        System.out.println("New Admin added successfully..!");
    }
    public void addBook(String bName,String authorName,String ISBN,int quantity,String category,String publication,int edition){
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
                System.out.println("Changes has been made successfully..!");
                return;
            }
        }
        throw new BookNotFoundException();
    }

    public void deleteBook(String isbn){
        for(Book i: allBooks){
            if(i.getISBN().equals(isbn)){
                allBooks.remove(i);
                System.out.println("Book deleted successfully..!");
                return;
            }
        }
        throw new BookNotFoundException();
    }



    public void sortByName(){
        Comparator<Book> compName=new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getbName().compareTo(o2.getbName());
            }
        };
        Collections.sort(allBooks,compName);
    }

    public void sortByQuantity(){
        Comparator<Book> compQuantity=new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getQuantity()>o2.getQuantity()?1:-1;
            }
        };
        Collections.sort(allBooks,compQuantity);
    }

    public Book searchBookByName(String bookName){
        for(Book book:allBooks){
            if(book.getbName().equalsIgnoreCase(bookName)){
                return  book;
            }
        }
        throw new BookNotFoundException();
    }

    public  Book searchBookByISBN(String isbn){
        for(Book book:allBooks){
            if(book.getISBN().equals(isbn)){
                return book;
            }
        }
        throw new BookNotFoundException();
    }



}
