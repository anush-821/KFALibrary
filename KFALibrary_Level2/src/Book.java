/**
 * Book is a specific kind of LibraryItem that also has an author.
 */
public class Book extends LibraryItem {
    private String author;

    public Book(String isbn, String title, double price, String author) {
        super(isbn, title, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + author;
    }
}
