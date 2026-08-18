/**
 * LibraryItem is the base class for anything the library owns.
 * Book (and any future item type, e.g. Magazine, DVD) extends this.
 */
public class LibraryItem {
    protected String isbn;
    protected String title;
    protected double price;

    public LibraryItem(String isbn, String title, double price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        // %-15s / %-30s left-align the text in a fixed-width column so printed
        // catalogues line up neatly instead of looking ragged.
        return String.format("%-15s | %-30s | $%.2f", isbn, title, price);
    }
}
