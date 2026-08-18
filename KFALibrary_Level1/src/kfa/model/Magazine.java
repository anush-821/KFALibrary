package kfa.model;

// Magazine deliberately does NOT implement Renewable because library rules don't allow renewing journals
public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, String isbn, double price, int issueNumber) {
        super(title, isbn, price);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }

    @Override
    public int getLendingPeriodDays() {
        return 7; // Magazines are issued for 1 week
    }

    @Override
    public String toString() {
        return "[Tech Journal Issue #" + issueNumber + "] " + getTitle() + " - Rs " + String.format("%.2f", getPrice()) +
                " (" + (isAvailable() ? "Available" : "Checked Out") + ")";
    }
}