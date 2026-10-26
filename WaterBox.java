package aquareach;

/**
 * A water box containing a fixed number of bottles.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class WaterBox extends WaterItem {

    /** Number of bottles in each box. */
    private final int bottlesPerBox;

    /**
     * @param name item name
     * @param quantity number of boxes available
     * @param bottlesPerBox bottles in each box (must be positive)
     */
    public WaterBox(String name, int quantity, int bottlesPerBox) {
        super(name, quantity);
        if (bottlesPerBox <= 0)
            throw new IllegalArgumentException("Bottles per box must be positive.");
        this.bottlesPerBox = bottlesPerBox;
    }

    /** @return bottles per box */
    public int getBottlesPerBox() { return bottlesPerBox; }

    @Override
    public void displayInfo() {
        System.out.println("Box: " + getName() +
                "\nBottles per box: " + bottlesPerBox +
                "\nQuantity Available: " + getQuantityAvailable());
    }
}
