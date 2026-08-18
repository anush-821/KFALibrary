import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Library holds the "grown up" catalogue built with the Java Collections
 * Framework: Sections C (ArrayList) and D (HashSet / HashMap).
 */
public class Library {

    // ---------- Section C: the catalogue itself ----------
    private ArrayList<LibraryItem> catalogue = new ArrayList<>();
    private ArrayList<LibraryItem> removalHistory = new ArrayList<>();

    // ---------- Section D: fast-lookup structures ----------
    private HashSet<String> currentlyBorrowed = new HashSet<>();
    private HashMap<String, Book> isbnIndex = new HashMap<>();

    // ================= C1: basic catalogue operations =================

    public void addItem(LibraryItem item) {
        catalogue.add(item);
        // Keep the fast-lookup index in sync whenever a Book is added.
        if (item instanceof Book) {
            isbnIndex.put(item.getIsbn(), (Book) item);
        }
    }

    public boolean removeItem(String isbn) {
        for (int i = 0; i < catalogue.size(); i++) {
            if (catalogue.get(i).getIsbn().equals(isbn)) {
                LibraryItem removed = catalogue.remove(i);
                removalHistory.add(removed); // C3: keep it for undo
                isbnIndex.remove(isbn);
                return true;
            }
        }
        return false;
    }

    public ArrayList<LibraryItem> searchByTitle(String keyword) {
        ArrayList<LibraryItem> matches = new ArrayList<>();
        String keywordLower = keyword.toLowerCase();
        for (LibraryItem item : catalogue) {
            if (item.getTitle().toLowerCase().contains(keywordLower)) {
                matches.add(item);
            }
        }
        return matches;
    }

    // ================= C2: sorting with Comparator =================

    public void sortByPriceAscending() {
        Collections.sort(catalogue, new Comparator<LibraryItem>() {
            @Override
            public int compare(LibraryItem a, LibraryItem b) {
                return Double.compare(a.getPrice(), b.getPrice());
            }
        });
    }

    public void sortByTitleAlphabetically() {
        // Lambda version of a Comparator, doing the same job as the
        // anonymous-class version above but more concisely.
        Collections.sort(catalogue, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }

    public void printCatalogue() {
        for (LibraryItem item : catalogue) {
            System.out.println(item);
        }
    }

    // ================= C3: undo the last removal =================

    public void undoRemove() {
        if (removalHistory.isEmpty()) {
            System.out.println("Nothing to undo — removal history is empty.");
            return;
        }
        LibraryItem lastRemoved = removalHistory.remove(removalHistory.size() - 1);
        catalogue.add(lastRemoved);
        if (lastRemoved instanceof Book) {
            isbnIndex.put(lastRemoved.getIsbn(), (Book) lastRemoved);
        }
        System.out.println("Restored: " + lastRemoved);
    }

    // ================= D1: HashSet — stop the double-borrow =================

    public boolean borrow(String isbn) {
        // HashSet.add() itself already returns false if the item is present,
        // so this one line does exactly what's needed.
        return currentlyBorrowed.add(isbn);
    }

    public boolean returnBook(String isbn) {
        return currentlyBorrowed.remove(isbn);
    }

    // ================= D2: HashMap — instant ISBN lookup =================

    public Book findByIsbn(String isbn) {
        // HashMap.get() computes a hash of the key and jumps straight to the
        // matching bucket — roughly O(1) on average. Scanning the ArrayList
        // instead is O(n): with a catalogue of thousands of books, that's
        // thousands of comparisons instead of one hash calculation.
        return isbnIndex.get(isbn);
    }

    // ================= D3: HashMap — most-borrowed report =================

    public static void printMostBorrowed(String[] borrowLog) {
        HashMap<String, Integer> borrowFrequency = new HashMap<>();
        for (String title : borrowLog) {
            // getOrDefault avoids a separate containsKey() check: if title
            // isn't in the map yet, treat its current count as 0.
            int currentCount = borrowFrequency.getOrDefault(title, 0);
            borrowFrequency.put(title, currentCount + 1);
        }

        String mostBorrowedTitle = null;
        int highestCount = -1;
        for (Map.Entry<String, Integer> entry : borrowFrequency.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                mostBorrowedTitle = entry.getKey();
            }
        }

        System.out.println("Borrow counts: " + borrowFrequency);
        System.out.println("Most borrowed: \"" + mostBorrowedTitle + "\" (" + highestCount + " times)");
    }
}
