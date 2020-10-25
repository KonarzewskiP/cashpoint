package bankomat.errors;

public class InvalidUserOrPasswordException extends Exception {
    public InvalidUserOrPasswordException(String message) {
        super(message);
    }
}
