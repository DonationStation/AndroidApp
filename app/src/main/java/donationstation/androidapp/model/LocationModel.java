package donationstation.androidapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private List<Location> items;

    public LocationModel() {
        items = new ArrayList<>();
    }

    public void addItem(Location item) {
        items.add(item);
    }

    public List<Location> getItems() {
        return items;
    }

    public Location findItemByKey(int key) {
        for (Location d : items) {
            if (d.getKey() == key) return d;
        }
        return null;
    }
}
