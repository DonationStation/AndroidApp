package donationstation.androidapp.model;

import com.google.firebase.database.DatabaseReference;

public class DonationItem {
    private String date;
    private String time;
    private String location;
    private String shortDes;
    private String fullDes;
    private double value;
    private String category;



    public DonationItem(String date, String time, String location, String category,
                        double value, String shortDes, String fullDes){
        this.date = date;
        this.time = time;
        this.location = location;
        this.category = category;
        this.value = value;
        this.shortDes = shortDes;
        this.fullDes = fullDes;
    }

    public String getDate() { return date; }

    public String getTime() {return time;}

    public String getLocation() { return location; }

    public String getShortDes() { return shortDes; }

    public String getFullDes() { return fullDes; }

    public double getValue() { return value; }

    public String getCategory() { return category; }

    public void setDate(String date) { this.date = date; }

    public void setTime(String time) { this.time = time; }

    public void setLocation(String location) { this.location = location; }

    public void setShortDes(String shortDes) { this.shortDes = shortDes; }

    public void setFullDes(String fullDes) { this.fullDes = fullDes; }

    public void setValue(int value) { this.value = value; }

    public void setCategory(String category) { this.category = category; }
}
