package donationstation.androidapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private List<Location> items;

    /**
     * empty constructor for creating array list
     */
    public LocationModel() {
        items = new ArrayList<>();
    }

    /**
     * adds location to array list
     * @param item location to be added
     */
    public void addItem(Location item) {
        items.add(item);
    }

    /**
     * list of locations
     * @return gets the locations in the list
     */
    public List<Location> getItems() {
        return items;
    }

    /**
     * finds location in list by key value
     * @param key the location's key
     * @return the location that corresponds
     */
    public Location findItemByKey(int key) {
        for (Location d : items) {
            if (d.getKey() == key) return d;
        }
        return null;
    }
}
