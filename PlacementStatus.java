package aquareach;

/**
 * Possible statuses of a {@link Placement}.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public enum PlacementStatus {

    /** Item was donated directly to a location. */
    DONATED("Donated"),

    /** Item was delivered from existing stock to a location. */
    DELIVERED("Delivered");

    private final String label;

    PlacementStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
