package kfa.exception;

// Thrown when an item is returned past its due date
public class ItemOverdueException extends Exception {
    private int daysOverdue;

    public ItemOverdueException(int daysOverdue, String message) {
        super(message);
        this.daysOverdue = daysOverdue;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }
}