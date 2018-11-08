package donationstation.androidapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Donation {
    public static final Donation INSTANCE = new Donation();

    private List<DonationItem> items;

    private Donation() {
        items = new ArrayList<>();
    }

    public void addItem(DonationItem item) {
        items.add(item);
    }

    public List<DonationItem> getItems() {
        return items;
    }

    public DonationItem findItemByCat(String category) {
        for (DonationItem d : items) {
            if (d.getCategory() == category) return d;
        }
        return null;
    }
}
