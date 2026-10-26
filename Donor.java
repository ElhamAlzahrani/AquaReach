package aquareach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A donor in the AquaReach system: ID, name and a list of donations
 * (composition).
 *
 * Implements Comparable for alphabetical sorting (name, then ID so that
 * two donors with the same name are not treated as duplicates) and
 * Cloneable for copying.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class Donor implements Cloneable, Comparable<Donor> {

    /** Global counter used to assign unique donor IDs. */
    private static int counter = 1;

    private final Integer donorID;
    private final String donorName;
    private ArrayList<Donation> donationList;

    /**
     * Creates a donor with an auto-generated ID.
     *
     * @param donorName donor name (not blank)
     */
    public Donor(String donorName) {
        if (donorName == null || donorName.isBlank())
            throw new IllegalArgumentException("Donor name cannot be empty.");
        this.donorID = counter++;
        this.donorName = donorName.trim();
        this.donationList = new ArrayList<>();
    }

    /** @return donor ID */
    public Integer getDonorID() { return donorID; }

    /** @return donor name */
    public String getDonorName() { return donorName; }

    /**
     * Adds a donation to this donor.
     *
     * @param d donation (not null)
     */
    public void addDonation(Donation d) {
        if (d == null)
            throw new IllegalArgumentException("Donation cannot be null.");
        donationList.add(d);
    }

    /** @return read-only view of this donor's donations */
    public List<Donation> getDonationList() {
        return Collections.unmodifiableList(donationList);
    }

    /**
     * Creates a copy of this donor with its own independent donation list.
     * (Donation objects themselves are shared.)
     *
     * @return cloned donor
     */
    @Override
    public Donor clone() {
        try {
            Donor copy = (Donor) super.clone();
            copy.donationList = new ArrayList<>(this.donationList);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Donor is Cloneable", e);
        }
    }

    @Override
    public int compareTo(Donor other) {
        int byName = this.donorName.compareToIgnoreCase(other.donorName);
        return byName != 0 ? byName : this.donorID.compareTo(other.donorID);
    }

    @Override
    public String toString() {
        return "Donor ID: " + donorID + ", Name: " + donorName +
                ", Donations Count: " + donationList.size();
    }
}
