public class MainSectionC {
    public static void main(String[] args) {
        Library library = new Library();

        library.addItem(new Book("001", "The Hobbit", 12.50, "J.R.R. Tolkien"));
        library.addItem(new Book("002", "Dune", 15.00, "Frank Herbert"));
        library.addItem(new Book("003", "1984", 8.75, "George Orwell"));
        library.addItem(new Book("004", "Foundation", 20.25, "Isaac Asimov"));

        System.out.println("===== C1: addItem / searchByTitle =====");
        System.out.println("Search results for \"the\":");
        for (LibraryItem item : library.searchByTitle("the")) {
            System.out.println("  " + item);
        }

        System.out.println("\n===== C2: sort by price ascending =====");
        library.sortByPriceAscending();
        library.printCatalogue();

        System.out.println("\n===== C2: sort alphabetically by title =====");
        library.sortByTitleAlphabetically();
        library.printCatalogue();

        System.out.println("\n===== C1: removeItem =====");
        boolean removed = library.removeItem("003");
        System.out.println("Removed \"1984\"? " + removed);
        boolean removedAgain = library.removeItem("999");
        System.out.println("Removed non-existent ISBN 999? " + removedAgain);

        System.out.println("\nCatalogue after removal:");
        library.printCatalogue();

        System.out.println("\n===== C3: undoRemove =====");
        library.undoRemove();
        System.out.println("Catalogue after undo:");
        library.printCatalogue();

        // Calling undo again with nothing left to restore.
        library.undoRemove();
    }
}
