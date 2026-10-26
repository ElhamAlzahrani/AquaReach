package aquareach;

import java.util.Scanner;

/**
 * Console entry point of the AquaReach system.
 *
 * @author Reham Alhawsawi, Elham Alzahrani
 */
public class AquaReach {

    private static final double BOTTLE_SIZE_LITERS = 0.5;
    private static final int BOTTLES_PER_BOX = 6;
    private static final int COOLER_CAPACITY_LITERS = 20;

    private static final String[] LOCATION_TYPES =
            {"Mosque", "Hospital", "University", "Walkway", "Park"};

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AquaReachSystem system = new AquaReachSystem();
        int choice;

        do {
            System.out.println("\n===== AquaReach Menu =====");
            System.out.println("1) Add Donation");
            System.out.println("2) View Distribution Records");
            System.out.println("3) Donation Summary Report");
            System.out.println("4) Exit");
            choice = readInt("Choice: ", 1, 4);

            switch (choice) {
                case 1 -> addDonation(system);
                case 2 -> viewRecords(system);
                case 3 -> showSummary(system);
                case 4 -> System.out.println("Goodbye.");
            }
        } while (choice != 4);
    }

    // ---------------------------------------------------------------
    // 1) Add donation
    // ---------------------------------------------------------------
    private static void addDonation(AquaReachSystem system) {
        String donorName = readText("\nDonor name: ");

        System.out.println("\nSelect donation type:");
        System.out.println("1) Water Bottles");
        System.out.println("2) Water Boxes");
        System.out.println("3) Water Coolers");
        int type = readInt("Choice: ", 1, 3);

        int qty = readInt("Enter quantity: ", 1, Integer.MAX_VALUE);

        System.out.println("\nSelect distribution location:");
        for (int i = 0; i < LOCATION_TYPES.length; i++) {
            System.out.println((i + 1) + ") " + LOCATION_TYPES[i]);
        }
        int locChoice = readInt("Choice: ", 1, LOCATION_TYPES.length);
        String locName = readText("Enter name: ");

        WaterItem item = switch (type) {
            case 1 -> new WaterBottle("Bottle", qty, BOTTLE_SIZE_LITERS);
            case 2 -> new WaterBox("Box", qty, BOTTLES_PER_BOX);
            default -> new WaterCooler("Cooler", qty, COOLER_CAPACITY_LITERS);
        };

        try {
            Donor donor = system.findOrCreateDonor(donorName);
            Location loc = system.findOrCreateLocation(locName, LOCATION_TYPES[locChoice - 1]);
            system.recordDonation(donor, item, loc);
            System.out.println("Donation added successfully!");
        } catch (WaterNotAvailableException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        pause("Press Enter to continue...");
    }

    // ---------------------------------------------------------------
    // 2) View distribution records
    // ---------------------------------------------------------------
    private static void viewRecords(AquaReachSystem system) {
        System.out.println("\n===== Distribution Records =====");

        if (system.getPlacements().isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Location l : system.getLocations()) {
                System.out.println(l);
                for (Placement p : l.getPlacements()) {
                    System.out.println(p);
                }
            }
        }
        pause("Press Enter to return...");
    }

    // ---------------------------------------------------------------
    // 3) Summary report
    // ---------------------------------------------------------------
    private static void showSummary(AquaReachSystem system) {
        System.out.println("\n===== Donation Summary Report =====");

        int bottles = 0, boxes = 0, coolers = 0;
        for (Placement p : system.getPlacements()) {
            if (p.getStatus() != PlacementStatus.DONATED) continue;
            WaterItem w = p.getItem();
            if (w instanceof WaterBottle) bottles += p.getAmount();
            else if (w instanceof WaterBox) boxes += p.getAmount();
            else if (w instanceof WaterCooler) coolers += p.getAmount();
        }

        if (bottles + boxes + coolers == 0) {
            System.out.println("No donation records found.");
        } else {
            System.out.println("Total Water Bottles: " + bottles);
            System.out.println("Total Water Boxes: " + boxes);
            System.out.println("Total Water Coolers: " + coolers);
            System.out.println("Number of Donors: " + system.getDonors().size());
        }
        pause("Press Enter to return...");
    }

    // ---------------------------------------------------------------
    // Input helpers (no crash on invalid input)
    // ---------------------------------------------------------------
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {
                // fall through to the error message
            }
            System.out.println("Invalid input. Please enter a number"
                    + (max == Integer.MAX_VALUE ? " >= " + min : " between " + min + " and " + max) + ".");
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("This field cannot be empty.");
        }
    }

    private static void pause(String message) {
        System.out.println(message);
        sc.nextLine();
    }
}
