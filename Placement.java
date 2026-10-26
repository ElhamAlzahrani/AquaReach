package aquareach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A placement/distribution event: which item, how many, to which location,
 * with what status and when.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class Placement {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final WaterItem item;
    private final int amount;
    private final LocalDateTime date;
    private final PlacementStatus status;
    private final Location location;

    /**
     * @param item water item placed (not null)
     * @param amount amount placed (must be positive)
     * @param status placement status (not null)
     * @param loc target location (not null)
     */
    public Placement(WaterItem item, int amount, PlacementStatus status, Location loc) {
        if (item == null || status == null || loc == null)
            throw new IllegalArgumentException("Item, status and location are required.");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        this.item = item;
        this.amount = amount;
        this.status = status;
        this.location = loc;
        this.date = LocalDateTime.now();
    }

    /** @return location */
    public Location getLocation() { return location; }

    /** @return item placed */
    public WaterItem getItem() { return item; }

    /** @return quantity placed */
    public int getAmount() { return amount; }

    /** @return placement status */
    public PlacementStatus getStatus() { return status; }

    /** @return placement date and time */
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return "  Placement > Location: " + location.getName() +
                ", Item: " + item.getName() +
                ", Amount: " + amount +
                ", Status: " + status +
                ", Date: " + date.format(FORMAT);
    }
}
