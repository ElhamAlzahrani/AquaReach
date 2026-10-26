package aquareach;

/**
 * A water bottle item with a size in liters.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class WaterBottle extends WaterItem {

    /** Bottle size in liters (e.g., 0.5). */
    private final double sizeInLiters;

    /**
     * @param name item name
     * @param quantity quantity available
     * @param sizeInLiters size of each bottle in liters (must be positive)
     */
    public WaterBottle(String name, int quantity, double sizeInLiters) {
        super(name, quantity);
        if (sizeInLiters <= 0)
            throw new IllegalArgumentException("Bottle size must be positive.");
        this.sizeInLiters = sizeInLiters;
    }

    /** @return bottle size in liters */
    public double getSizeInLiters() { return sizeInLiters; }

    @Override
    public void displayInfo() {
        System.out.println("Bottle: " + getName() +
                "\nSize: " + sizeInLiters + " L" +
                "\nQuantity Available: " + getQuantityAvailable());
    }
}
