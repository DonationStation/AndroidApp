package donationstation.androidapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Donation {
    public static final Donation INSTANCE = new Donation();

    private List<DataItem> items;

    private Donation() {
        items = new ArrayList<>();
    }

    public void addItem(DataItem item) {
        items.add(item);
    }

    public List<DataItem> getItems() {
        return items;
    }

}
