package donationstation.androidapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a Donation
 */
public class Donation {
    public static final Donation INSTANCE = new Donation();

    private final List<DonationItem> items;

    private Donation() {
        items = new ArrayList<>();
    }

    /**
     * adding the item to the list
     * @param item donation item to be added
     */
    public void addItem(DonationItem item) {
        items.add(item);
    }

    /**
     * getting the list of donation items
     * @return donation items in a list
     */
    public List<DonationItem> getItems() {
        return items;
    }

    /**
     * finding donation in list by category
     * @param category the category to find
     * @return the items with that category
     */
    public DonationItem findItemByCat(String category) {
        for (DonationItem d : items) {
            if (d.getCategory() == category) return d;
        }
        return null;
    }
}
