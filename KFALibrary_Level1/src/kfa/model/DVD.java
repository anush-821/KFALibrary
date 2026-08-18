package kfa.model;

public class DVD extends LibraryItem implements Renewable {
    private int durationMinutes;

    public DVD(String title, String isbn, double price, int durationMinutes) {
        super(title, isbn, price);
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public int getLendingPeriodDays() {
        return 5; // Practical lab DVDs are issued for 5 days
    }

    @Override
    public void renew(int extraDays) {
        System.out.println("DVD '" + getTitle() + "' extended by " + extraDays + " days.");
    }

    @Override
    public String toString() {
        return "[Video Lecture " + durationMinutes + " mins] " + getTitle() + " - Rs " + String.format("%.2f", getPrice()) +
                " (" + (isAvailable() ? "Available" : "Checked Out") + ")";
    }
}