package kfa.exception;

// Thrown when a student requests an item that is already borrowed
public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}