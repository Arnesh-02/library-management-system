package exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException(){
        super("Sorry..! Book is not found");
    }
}
