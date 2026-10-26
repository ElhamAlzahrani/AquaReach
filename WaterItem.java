package aquareach;

/**
 * Abstract parent of all water items (bottles, boxes, coolers).
 * Holds the shared state (ID, name, stock) and the shared
 * distribution / restocking logic.
 *
 * Implements Comparable for alphabetical sorting by name.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public abstract class WaterItem implements Comparable<WaterItem>, Distributable {

    /** Global counter used to assign unique IDs. */
    private static int counter = 1;

    /** Unique ID of the item. */
    private final Integer itemID;

    /** Item name (e.g., "Bottle"). */
    private String itemName;

    /** Quantity currently in stock. */
    private int quantityAvailable;

    /**
     * Constructs a WaterItem with a unique ID.
     *
     * @param name the item name
     * @param quantityAvailable initial stock (must not be negative)
     * @throws IllegalArgumentException if name is blank or quantity is negative
     */
    protected WaterItem(String name, int quantityAvailable) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Item name cannot be empty.");
        if (quantityAvailable < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");
        this.itemID = counter++;
        this.itemName = name;
        this.quantityAvailable = quantityAvailable;
    }

    /** @return the unique item ID */
    public Integer getItemID() { return itemID; }

    /** @return item name */
    public String getName() { return itemName; }

    /**
     * @param name new item name
     * @throws IllegalArgumentException if name is blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Item name cannot be empty.");
        this.itemName = name;
    }

    /** @return the available quantity */
    public int getQuantityAvailable() { return quantityAvailable; }

    /**
     * @param quantityAvailable new stock level
     * @throws IllegalArgumentException if negative
     */
    public void setQuantityAvailable(int quantityAvailable) {
        if (quantityAvailable < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantityAvailable = quantityAvailable;
    }

    /** {@inheritDoc} */
    @Override
    public void distribute(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        if (amount > quantityAvailable)
            throw new IllegalArgumentException("Not enough stock to distribute.");
        quantityAvailable -= amount;
    }

    /** {@inheritDoc} */
    @Override
    public void restock(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        quantityAvailable += amount;
    }

    /** Displays details about the item. Implemented by subclasses. */
    public abstract void displayInfo();

    @Override
    public String toString() {
        return "Item ID: " + itemID + ", Name: " + itemName +
               ", Quantity Available: " + quantityAvailable;
    }

    /** Equality is based on the unique item ID. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WaterItem)) return false;
        return itemID.equals(((WaterItem) obj).itemID);
    }

    @Override
    public int hashCode() {
        return itemID.hashCode();
    }

    /** Compares items alphabetically by name (case-insensitive). */
    @Override
    public int compareTo(WaterItem other) {
        return this.itemName.compareToIgnoreCase(other.itemName);
    }
}

