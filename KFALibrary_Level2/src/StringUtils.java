/**
 * Section A — string cleaning and formatting helpers.
 * Everything here uses only trim(), split(), substring(), toUpperCase()/toLowerCase()
 * and loops/StringBuilder — no regex.
 */
public class StringUtils {

    /**
     * A1. Cleans up a messy title:
     *  - trims leading/trailing whitespace
     *  - collapses runs of multiple spaces into a single space
     *  - converts to Title Case (first letter of each word capitalised, rest lowercase)
     */
    public static String sanitizeTitle(String raw) {
        // Step 1: trim outer whitespace.
        String trimmed = raw.trim();

        // Step 2: collapse any run of spaces into a single space.
        // We do this manually with a loop instead of a regex like " +".
        StringBuilder collapsed = new StringBuilder();
        boolean lastCharWasSpace = false;
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (c == ' ') {
                if (!lastCharWasSpace) {
                    collapsed.append(c);
                }
                lastCharWasSpace = true;
            } else {
                collapsed.append(c);
                lastCharWasSpace = false;
            }
        }

        // Step 3: split on the single space we know now separates every word.
        String[] words = collapsed.toString().split(" ");

        // Step 4: capitalise the first letter of each word, lowercase the rest.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue; // guards against an accidental empty token
            }
            String firstLetter = word.substring(0, 1).toUpperCase();
            String rest = word.substring(1).toLowerCase();
            result.append(firstLetter).append(rest);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * A2. Builds a simple multi-line receipt using StringBuilder.
     */
    public static String generateReceiptText(String memberName, LibraryItem item) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append("Member Name : ").append(memberName).append("\n");
        sb.append("Item Title  : ").append(item.getTitle()).append("\n");
        sb.append("Due Date    : [DUE_DATE_PLACEHOLDER]\n");
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }

    /**
     * A2. Demonstrates why == is dangerous for comparing ISBN strings, and why
     * .equals() is correct.
     */
    public static void isbnEqualityDemo() {
        String isbn1 = "978-0141537528";
        // new String(...) forces Java to create a brand-new object on the heap,
        // instead of reusing the cached literal from the string pool.
        String isbn2 = new String("978-0141537528");

        System.out.println("isbn1 == isbn2        : " + (isbn1 == isbn2));
        System.out.println("isbn1.equals(isbn2)   : " + isbn1.equals(isbn2));
    }
}
