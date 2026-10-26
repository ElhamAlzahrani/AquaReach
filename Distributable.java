package aquareach;

/**
 * Defines the behaviors of any distributable water item:
 * it can be distributed and restocked.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public interface Distributable {

    /**
     * Distributes a specific amount of the item.
     *
     * @param amount quantity to distribute (must be positive and not exceed stock)
     * @throws IllegalArgumentException if the amount is invalid or exceeds stock
     */
    void distribute(int amount);

    /**
     * Restocks a specific amount of the item.
     *
     * @param amount quantity to add (must be positive)
     * @throws IllegalArgumentException if the amount is not positive
     */
    void restock(int amount);
}