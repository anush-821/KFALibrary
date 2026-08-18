import kfa.model.*;
import kfa.exception.*;
import kfa.service.*;

public class Main {

    // Generates Student ID (e.g., "ROBHD457" for Robin Hood)
    public static String generateMemberId(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "KFA000";
        }

        String cleanName = fullName.trim();
        String[] nameParts = cleanName.split(" ");

        // First 3 letters of first name
        String firstPart = nameParts[0];
        if (firstPart.length() >= 3) {
            firstPart = firstPart.substring(0, 3).toUpperCase();
        } else {
            firstPart = firstPart.toUpperCase();
        }

        // First 2 letters of last name, or "NP" if single name
        String lastPart = "";
        if (nameParts.length > 1) {
            String lastName = nameParts[nameParts.length - 1];
            if (lastName.length() >= 2) {
                lastPart = lastName.substring(0, 2).toUpperCase();
            } else {
                lastPart = lastName.toUpperCase();
            }
        } else {
            lastPart = "NP"; // Default fallback for single-word names
        }

        int randomCode = (int) (Math.random() * 900) + 100;
        return firstPart + lastPart + randomCode;
    }

    // Validates 13-digit ISBN without using regular expressions
    public static boolean isValidIsbn(String isbn) {
        if (isbn == null || isbn.length() != 13) return false;
        if (isbn.charAt(0) == '0') return false; // Rule: cannot start with 0

        // Ensures all characters are numeric digits
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Builds a multi-line output string using StringBuilder
    public static String buildCatalogueReport(LibraryItem[] items, String searchKeyword) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n===============================================\n");
        builder.append("       KFA LIBRARY SEARCH & CATALOGUE REPORT   \n");
        builder.append("===============================================\n");

        for (LibraryItem item : items) {
            String titleLower = item.getTitle().toLowerCase();
            String queryLower = (searchKeyword == null) ? "" : searchKeyword.toLowerCase();

            // Adds matching books straight to the StringBuilder
            if (queryLower.isEmpty() || titleLower.contains(queryLower)) {
                builder.append("• ").append(item.getTitle())
                        .append(" | Status: ").append(item.isAvailable() ? "In Stock" : "Borrowed")
                        .append(" | Return Window: ").append(item.getLendingPeriodDays()).append(" Days\n");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("    KFA COLLEGE LIBRARY AUTOMATION SYSTEM (KATHMANDU)    ");
        System.out.println("=========================================================\n");

        // Textbook objects based on CS subjects
        Book b1 = new Book("Java Programming and Object-Oriented Concepts", "Herbert Schildt", "9780070417137", 750.00);
        Book b2 = new Book("Data Communications and Networking Essentials", "Andrew Tanenbaum", "9780072947753", 1150.00);
        Book b3 = new Book("Computer Architecture and Organization", "John Hennessy", "9780732934330", 980.00);
        Book b4 = new Book("Ethical Hacking and Cyber Security Fundamentals", "Michael Simpson", "9780728027196", 890.00);

        Magazine m1 = new Magazine("Nepal IT & Tech Review", "9771234567890", 200.00, 102);
        DVD d1 = new DVD("Ethical Hacking & Cyber Sec Practical Labs", "9789937854123", 450.00, 120);

        // Polymorphic array containing all item types
        LibraryItem[] catalog = new LibraryItem[] { b1, b2, b3, b4, m1, d1 };

        System.out.println("--- SECTION A & B: CS/IT RESOURCE CATALOGUE ---");
        for (LibraryItem item : catalog) {
            // Polymorphism calls child-specific toString() and getLendingPeriodDays() automatically
            System.out.println(item);
            System.out.println("   Lending Duration: " + item.getLendingPeriodDays() + " days");
        }
        System.out.println("\nTotal Registered Books: " + Book.getTotalBooks());

        System.out.println("\n--- SECTION C: LIBRARY ISSUING & RETURN TRANSACTIONS ---");
        LibrarySystem library = new LibrarySystem();

        // 1. Valid issue
        handleIssue(library, b1);

        // 2. Issuing an already taken book (Triggers BookNotAvailableException)
        handleIssue(library, b1);

        // 3. Returning late (Triggers ItemOverdueException)
        handleReturn(library, b1, 4);

        // 4. Returning on time
        handleReturn(library, b2, 0);

        System.out.println("\n--- SECTION D: STUDENT ID GENERATION & SEARCH ---");
        System.out.println("Member ID (Anush Shiwakoti) : " + generateMemberId("Anush Shiwakoti"));
        System.out.println("Member ID (Amit Karki)         : " + generateMemberId("Amit Karki"));
        System.out.println("Member ID (Gita Adhikari)   : " + generateMemberId("Gita Adhikari"));

        System.out.println("\nISBN Validation Check:");
        System.out.println("  '9780090617431' (Valid ISBN)        : " + isValidIsbn("9780090617431"));
        System.out.println("  '0780090617431' (Invalid - Starts 0): " + isValidIsbn("0780090617431"));

        // Filter search using keyword "Cyber"
        System.out.println(buildCatalogueReport(catalog, "Cyber"));
    }

    private static void handleIssue(LibrarySystem library, LibraryItem item) {
        try {
            library.borrowItem(item);
        } catch (BookNotAvailableException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        } finally {
            // Confirmation line executed every time
            System.out.println("Transaction log finalized for: " + item.getTitle());
        }
    }

    private static void handleReturn(LibrarySystem library, LibraryItem item, int daysLate) {
        try {
            library.returnItem(item, daysLate);
        } catch (ItemOverdueException e) {
            System.out.println("Exception Caught: " + e.getMessage() + " [Days Overdue: " + e.getDaysOverdue() + "]");
        } finally {
            // Confirmation line executed every time
            System.out.println("Transaction log finalized for: " + item.getTitle());
        }
    }
}