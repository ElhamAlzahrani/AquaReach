package aquareach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A donation made by a donor: item, amount and date.
 * Part of the composition with {@link Donor}.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class Donation {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final WaterItem item;
    private final int amount;
    private final LocalDateTime date;

    /**
     * @param item donated item (not null)
     * @param amount amount donated (must be positive)
     */
    public Donation(WaterItem item, int amount) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null.");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        this.item = item;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    /** @return donated item */
    public WaterItem getItem() { return item; }

    /** @return donation amount */
    public int getAmount() { return amount; }

    /** @return donation date and time */
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return "Donation > Item: " + item.getName() +
                ", Amount: " + amount + ", Date: " + date.format(FORMAT);
    }
}
