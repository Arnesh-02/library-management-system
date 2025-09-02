package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static List<Book> allBooks=new ArrayList<>();
//    public static int bookId=0;
    private String ISBN;
    private String bName;
    private String authorName;
    private int quantity;
    private  String category;
    private String publication;
    private int edition;
    private Status status;
    private int price;
    private int borrowCount;

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Book(String bName, String authorName, String ISBN, int quantity, String category, String publication, int edition, int price) {
        this.bName = bName;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.category = category;
        this.publication = publication;
        this.edition = edition;
        this.price=price;
        this.status=Status.available;
        this.borrowCount=0;
        allBooks.add(this);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
                ", edition=" + edition +
                ", price=" + price +
                ", borrowCount="+borrowCount+
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(ISBN, book.ISBN) && Objects.equals(bName, book.bName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, bName);
    }
}
