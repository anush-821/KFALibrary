/**
 * Section B2 — plain array operations on Book[] (no Arrays.sort, no Collections).
 */
public class BookUtils {

    /**
     * Finds the priciest book with a simple linear scan.
     */
    public static Book findMostExpensive(Book[] books) {
        if (books.length == 0) {
            return null;
        }
        Book mostExpensive = books[0];
        for (int i = 1; i < books.length; i++) {
            if (books[i].getPrice() > mostExpensive.getPrice()) {
                mostExpensive = books[i];
            }
        }
        return mostExpensive;
    }

    /**
     * Reverses the array's element order in place, using two pointers
     * moving toward the middle and a temp variable to swap.
     */
    public static void reverseInPlace(Book[] books) {
        int left = 0;
        int right = books.length - 1;
        while (left < right) {
            Book temp = books[left];
            books[left] = books[right];
            books[right] = temp;
            left++;
            right--;
        }
    }
}
