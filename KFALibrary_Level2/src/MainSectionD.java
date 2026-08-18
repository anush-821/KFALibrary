public class MainSectionD {
    public static void main(String[] args) {
        Library library = new Library();
        library.addItem(new Book("001", "The Hobbit", 12.50, "J.R.R. Tolkien"));
        library.addItem(new Book("002", "Dune", 15.00, "Frank Herbert"));
        library.addItem(new Book("003", "1984", 8.75, "George Orwell"));

        System.out.println("===== D1: HashSet — stop the double-borrow =====");
        System.out.println("Borrow 001 (first time): " + library.borrow("001"));
        System.out.println("Borrow 001 (again, should fail): " + library.borrow("001"));
        System.out.println("Return 001: " + library.returnBook("001"));
        System.out.println("Borrow 001 (after return, should work again): " + library.borrow("001"));

        System.out.println("\n===== D2: HashMap — instant ISBN lookup =====");
        Book found = library.findByIsbn("002");
        System.out.println("Lookup for 002: " + found);
        Book notFound = library.findByIsbn("999");
        System.out.println("Lookup for 999: " + notFound);

        System.out.println("\n===== D3: HashMap — most-borrowed report =====");
        String[] borrowLog = {
                "1984", "Dune", "1984", "The Hobbit", "1984", "Dune"
        };
        Library.printMostBorrowed(borrowLog);
    }
}
