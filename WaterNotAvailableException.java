package aquareach;

/**
 * Checked exception thrown when a requested water item is not found
 * or when there is insufficient stock for distribution.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class WaterNotAvailableException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception with a message.
     *
     * @param msg error message
     */
    public WaterNotAvailableException(String msg) {
        super(msg);
    }
}

