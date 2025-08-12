package model;

import java.util.ArrayList;
import java.util.List;

public class Book implements Comparable {
    public static List<Book> allBooks=new ArrayList<>();
//    public static int bookId=0;
    private String ISBN;
    private String bName;
    private String authorName;
    private int quantity;
    private  String category;
    private String publication;
    private Status status;
    private int edition;

    public Book(String bName, String authorName, String ISBN, int quantity, String category, String publication, int edition) {
//        this.id = ++bookId;
        this.bName = bName;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.category = category;
        this.publication = publication;
        this.status = Status.available;
        this.edition = edition;
        allBooks.add(this);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }


    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", bName='" + bName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", publication='" + publication + '\'' +
                ", status=" + status +
                ", edition=" + edition +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
