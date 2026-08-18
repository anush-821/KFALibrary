package kfa.model;

public class Book extends LibraryItem implements Renewable {
    private String author;

    // Shared counter across all book objects to keep count of total copies
    private static int totalBooks = 0;

    public Book(String title, String author, String isbn, double price) {
        super(title, isbn, price);
        this.author = author;
        totalBooks++; // Increases global count every time a book is created
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public static int getTotalBooks() {
        return totalBooks;
    }

    @Override
    public int getLendingPeriodDays() {
        return 14; // Books are issued for 2 weeks
    }

    @Override
    public void renew(int extraDays) {
        System.out.println("Book '" + getTitle() + "' renewed for " + extraDays + " additional days.");
    }

    @Override
    public String toString() {
        return "[" + getIsbn() + "] " + getTitle() + " by " + author + " - Rs " + String.format("%.2f", getPrice()) +
                " (" + (isAvailable() ? "Available" : "Checked Out") + ")";
    }
}