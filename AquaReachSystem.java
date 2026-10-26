package aquareach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Main controller of the AquaReach system.
 *
 * Manages water items, donors, locations and placements, and provides
 * searching, distribution, donation recording and sorting.
 *
 * Aggregation: system -> items, donors, locations.
 * Composition: location -> placements, donor -> donations.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class AquaReachSystem {

    private final ArrayList<WaterItem> items = new ArrayList<>();
    private final TreeSet<Donor> donors = new TreeSet<>();
    private final TreeSet<Location> locations = new TreeSet<>();
    private final ArrayList<Placement> placements = new ArrayList<>();

    /** @return read-only list of water items */
    public List<WaterItem> getItems() { return Collections.unmodifiableList(items); }

    /** @return read-only list of all placements */
    public List<Placement> getPlacements() { return Collections.unmodifiableList(placements); }

    /** @return locations sorted by name */
    public List<Location> getLocations() { return new ArrayList<>(locations); }

    /** @return donors sorted by name */
    public List<Donor> getDonors() { return new ArrayList<>(donors); }

    /** Adds a new water item. */
    public void addItem(WaterItem item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null.");
        items.add(item);
    }

    /** Adds a donor to the system. */
    public void addDonor(Donor d) {
        if (d == null) throw new IllegalArgumentException("Donor cannot be null.");
        donors.add(d);
    }

    /** Adds a location to the system. */
    public void addLocation(Location l) {
        if (l == null) throw new IllegalArgumentException("Location cannot be null.");
        locations.add(l);
    }

    /**
     * Returns the existing donor with this name (case-insensitive),
     * or creates and registers a new one.
     *
     * @param name donor name
     * @return the existing or newly created donor
     */
    public Donor findOrCreateDonor(String name) {
        for (Donor d : donors) {
            if (d.getDonorName().equalsIgnoreCase(name.trim())) return d;
        }
        Donor created = new Donor(name);
        donors.add(created);
        return created;
    }

    /**
     * Returns the existing location with this name and type, or creates
     * and registers a new one. This guarantees a single Location object per
     * place, so no placements are lost.
     *
     * @param name location name
     * @param type location type
     * @return the existing or newly created location
     */
    public Location findOrCreateLocation(String name, String type) {
        Location candidate = new Location(name, type);
        for (Location l : locations) {
            if (l.compareTo(candidate) == 0) return l;
        }
        locations.add(candidate);
        return candidate;
    }

    /**
     * Searches for a water item by ID.
     *
     * @param id item ID
     * @return matching item
     * @throws WaterNotAvailableException if not found
     */
    public WaterItem searchItemByID(Integer id) throws WaterNotAvailableException {
        for (WaterItem w : items) {
            if (w.getItemID().equals(id)) return w;
        }
        throw new WaterNotAvailableException("Item ID not found: " + id);
    }

    /**
     * Distributes water from stock to a location (status: DELIVERED).
     *
     * @param itemID item ID
     * @param amount amount to distribute
     * @param loc destination location
     * @throws WaterNotAvailableException if the item is not found or stock is insufficient
     */
    public void placeWater(int itemID, int amount, Location loc) throws WaterNotAvailableException {
        placeWater(itemID, amount, loc, PlacementStatus.DELIVERED);
    }

    /**
     * Distributes water from stock to a location with a given status.
     *
     * @param itemID item ID
     * @param amount amount to distribute (must be positive)
     * @param loc destination location (not null)
     * @param status placement status
     * @throws WaterNotAvailableException if the item is not found or stock is insufficient
     */
    public void placeWater(int itemID, int amount, Location loc, PlacementStatus status)
            throws WaterNotAvailableException {
        if (loc == null) throw new IllegalArgumentException("Location cannot be null.");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");

        WaterItem w = searchItemByID(itemID);
        if (w.getQuantityAvailable() < amount)
            throw new WaterNotAvailableException("Not enough stock! Available: "
                    + w.getQuantityAvailable() + ", requested: " + amount);

        w.distribute(amount);

        Placement p = new Placement(w, amount, status, loc);
        loc.addPlacement(p);
        placements.add(p);
    }

    /**
     * Records a donation: registers the donor and item, links the donation
     * to the donor, and places the donated quantity at the location.
     *
     * @param donor the donor
     * @param item the donated item (its quantity is the donated amount)
     * @param loc the location receiving the donation
     * @throws WaterNotAvailableException if the placement fails
     */
    public void recordDonation(Donor donor, WaterItem item, Location loc)
            throws WaterNotAvailableException {
        int qty = item.getQuantityAvailable();
        if (qty <= 0)
            throw new IllegalArgumentException("Donated quantity must be positive.");

        addDonor(donor);
        addLocation(loc);
        addItem(item);
        donor.addDonation(new Donation(item, qty));
        placeWater(item.getItemID(), qty, loc, PlacementStatus.DONATED);
    }

    /**
     * Restocks an existing water item.
     *
     * @param itemID item ID
     * @param amount amount to add
     * @throws WaterNotAvailableException if the item is not found
     */
    public void restockWater(int itemID, int amount) throws WaterNotAvailableException {
        searchItemByID(itemID).restock(amount);
    }

    /** Sorts items alphabetically by name. */
    public void sortItems() { Collections.sort(items); }

    /**
     * Deletes a donor if they have no donations.
     *
     * @param d donor
     * @return true if deleted, false if the donor has donations
     */
    public boolean deleteDonor(Donor d) {
        if (!d.getDonationList().isEmpty()) return false;
        return donors.remove(d);
    }

    /**
     * Distributes and prints the result (success or error message).
     *
     * @param itemID item ID
     * @param amount amount to distribute
     * @param loc destination location
     */
    public void distribute(int itemID, int amount, Location loc) {
        try {
            placeWater(itemID, amount, loc);
            System.out.println("Distributed " + amount + " from item ID: " + itemID
                    + " to location: " + loc.getName());
        } catch (WaterNotAvailableException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
