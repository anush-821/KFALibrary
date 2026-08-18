public class MainSectionA {
    public static void main(String[] args) {
        System.out.println("===== A1: sanitizeTitle() =====");
        String[] messyTitles = {
                "   the GREAT gatsby   ",
                "harry    potter  and   the goblet OF fire",
                "  1984 "
        };
        for (String messy : messyTitles) {
            String clean = StringUtils.sanitizeTitle(messy);
            System.out.println("\"" + messy + "\"  ->  \"" + clean + "\"");
        }

        System.out.println("\n===== A2: generateReceiptText() =====");
        Book book = new Book("978-0141439514", "Pride and Prejudice", 9.99, "Jane Austen");
        String receipt = StringUtils.generateReceiptText("Anush Shiwakoti", book);
        System.out.println(receipt);

        System.out.println("===== A2: == vs .equals() on ISBNs =====");
        StringUtils.isbnEqualityDemo();
    }
}
