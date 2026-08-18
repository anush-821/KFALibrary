/**
 * Section B1 — models the physical library as a 5x10 grid of shelf slots.
 */
public class Shelf {

    /**
     * Places the ISBN in the first empty slot ("" means empty), scanning
     * shelf by shelf, slot by slot. Returns {shelfIndex, slotIndex}, or
     * {-1, -1} if the grid is full.
     */
    public static int[] placeOnShelf(String[][] shelves, String isbn) {
        for (int shelf = 0; shelf < shelves.length; shelf++) {
            for (int slot = 0; slot < shelves[shelf].length; slot++) {
                if (shelves[shelf][slot].isEmpty()) {
                    shelves[shelf][slot] = isbn;
                    return new int[]{shelf, slot};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Prints the shelf grid in a readable row-by-row layout.
     */
    public static void printShelves(String[][] shelves) {
        for (int shelf = 0; shelf < shelves.length; shelf++) {
            StringBuilder row = new StringBuilder("Shelf " + shelf + ": ");
            for (int slot = 0; slot < shelves[shelf].length; slot++) {
                String value = shelves[shelf][slot].isEmpty() ? "[ empty ]" : "[" + shelves[shelf][slot] + "]";
                row.append(value).append(" ");
            }
            System.out.println(row.toString());
        }
    }
}
