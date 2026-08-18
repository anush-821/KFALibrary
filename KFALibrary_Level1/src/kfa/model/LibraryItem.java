package kfa.model;

// Parent abstract class for all items in the KFA library
public abstract class LibraryItem {
    private String title;
    private String isbn;
    private double price;
    private boolean available;

    public LibraryItem(String title, String isbn, double price) {
        this.title = title;
        this.isbn = isbn;
        setPrice(price); // Using setter so validation logic runs automatically
        this.available = true; // Every new item added starts as available
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public double getPrice() { return price; }

    // Rejects negative pricing to keep data safe
    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid amount! Price cannot be negative. Defaulting to Rs 0.0");
            this.price = 0.0;
        } else {
            this.price = price;
        }
    }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Abstract method that child classes must override
    public abstract int getLendingPeriodDays();
}