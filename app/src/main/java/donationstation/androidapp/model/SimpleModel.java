package donationstation.androidapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SimpleModel {
    public static final SimpleModel INSTANCE = new SimpleModel();

    private List<Location> items;

    private SimpleModel() {
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
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}
