public class MainSectionB {
    public static void main(String[] args) {
        System.out.println("===== B1: Shelf grid =====");
        String[][] shelves = new String[5][10];
        // Initialise every slot to "" (empty) as required.
        for (int i = 0; i < shelves.length; i++) {
            for (int j = 0; j < shelves[i].length; j++) {
                shelves[i][j] = "";
            }
        }

        String[] isbnsToPlace = {"111-AAA", "222-BBB", "333-CCC"};
        for (String isbn : isbnsToPlace) {
            int[] position = Shelf.placeOnShelf(shelves, isbn);
            System.out.println("Placed " + isbn + " at shelf " + position[0] + ", slot " + position[1]);
        }
        System.out.println("\nCurrent shelf layout:");
        Shelf.printShelves(shelves);

        System.out.println("\n===== B2: findMostExpensive() and reverseInPlace() =====");
        Book[] books = {
                new Book("001", "The Hobbit", 12.50, "J.R.R. Tolkien"),
                new Book("002", "Dune", 15.00, "Frank Herbert"),
                new Book("003", "1984", 8.75, "George Orwell"),
                new Book("004", "Foundation", 20.25, "Isaac Asimov"),
                new Book("005", "Neuromancer", 11.40, "William Gibson")
        };

        System.out.println("Before reversing:");
        for (Book b : books) {
            System.out.println(b);
        }

        Book priciest = BookUtils.findMostExpensive(books);
        System.out.println("\nMost expensive book: " + priciest);

        BookUtils.reverseInPlace(books);
        System.out.println("\nAfter reversing:");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
