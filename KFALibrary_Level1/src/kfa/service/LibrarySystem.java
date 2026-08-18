package kfa.service;

import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.LibraryItem;

public class LibrarySystem {

    // Issue item logic
    public void borrowItem(LibraryItem item) throws BookNotAvailableException {
        if (!item.isAvailable()) {
            throw new BookNotAvailableException("Resource '" + item.getTitle() + "' is already borrowed by another student.");
        }
        item.setAvailable(false); // Mark as checked out
        System.out.println("Successfully issued: " + item.getTitle());
    }

    // Return item logic
    public void returnItem(LibraryItem item, int daysLate) throws ItemOverdueException {
        if (daysLate > 0) {
            item.setAvailable(true); // Return back to stock
            throw new ItemOverdueException(daysLate, "Late return! '" + item.getTitle() + "' is " + daysLate + " day(s) overdue.");
        }
        item.setAvailable(true); // Return back to stock
        System.out.println("Successfully returned: " + item.getTitle());
    }
}