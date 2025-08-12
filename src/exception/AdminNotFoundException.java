package exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException(String message) {
        super(message);
    }
    public AdminNotFoundException(){
        super("Admin not yet registered");
    }
}
