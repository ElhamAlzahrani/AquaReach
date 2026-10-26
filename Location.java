package aquareach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A physical distribution location (e.g., Mosque, Hospital) together with
 * the placements made there (composition).
 *
 * Implements Comparable for sorting by name, then type.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class Location implements Comparable<Location> {

    private final String name;
    private final String type;
    private final ArrayList<Placement> placements;

    /**
     * @param name location name (not blank)
     * @param type location type (not blank)
     */
    public Location(String name, String type) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Location name cannot be empty.");
        if (type == null || type.isBlank())
            throw new IllegalArgumentException("Location type cannot be empty.");
        this.name = name.trim();
        this.type = type.trim();
        this.placements = new ArrayList<>();
    }

    /**
     * Adds a placement to this location.
     *
     * @param p placement (not null)
     */
    public void addPlacement(Placement p) {
        if (p == null)
            throw new IllegalArgumentException("Placement cannot be null.");
        placements.add(p);
    }

    /** @return read-only view of placements at this location */
    public List<Placement> getPlacements() {
        return Collections.unmodifiableList(placements);
    }

    /** @return location name */
    public String getName() { return name; }

    /** @return location type */
    public String getType() { return type; }

    @Override
    public int compareTo(Location o) {
        int byName = this.name.compareToIgnoreCase(o.name);
        return byName != 0 ? byName : this.type.compareToIgnoreCase(o.type);
    }

    @Override
    public String toString() {
        return "Location: " + name + " (" + type + "), Placements: " + placements.size();
    }
}
