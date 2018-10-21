package donationstation.androidapp.model;

import java.util.ArrayList;
import java.util.List;

public class Donation {
    public static final Donation INSTANCE = new Donation();

    private List<Location> items;

    private Donation() {
        items = new ArrayList<>();
    }

    public void addItem(Location item) {
        items.add(item);
    }

    public List<Location> getItems() {
        return items;
    }

}
