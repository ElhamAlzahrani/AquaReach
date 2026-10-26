package aquareach;

/**
 * A water cooler item with a storage capacity.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class WaterCooler extends WaterItem {

    /** Water capacity of each cooler in liters. */
    private final int quantityStored;

    /**
     * @param name cooler name
     * @param quantity number of coolers available
     * @param quantityStored water stored inside each cooler (liters, must be positive)
     */
    public WaterCooler(String name, int quantity, int quantityStored) {
        super(name, quantity);
        if (quantityStored <= 0)
            throw new IllegalArgumentException("Stored quantity must be positive.");
        this.quantityStored = quantityStored;
    }

    /** @return water stored per cooler (liters) */
    public int getQuantityStored() { return quantityStored; }

    @Override
    public void displayInfo() {
        System.out.println("Cooler: " + getName() +
                "\nQuantity Stored: " + quantityStored + " L" +
                "\nQuantity Available: " + getQuantityAvailable());
    }
}
