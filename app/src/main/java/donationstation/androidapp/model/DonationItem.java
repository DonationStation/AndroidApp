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

    /**
     * empty constructor for donation item
     */
    public DonationItem() {

    }

    /**
     * creates a donation item
     * @param date day when item was donated
     * @param time time when item was donated
     * @param location where item was donated
     * @param category what kind of item was donated
     * @param value value of donated item
     * @param shortDes short description of item
     * @param fullDes full description of item
     */
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

    /**
     * gives the date when item was donated
     * @return date
     */
    public String getDate() { return date; }

    /**
     * gives time when item was donated
     * @return time
     */
    public String getTime() {return time;}

    /**
     * gives location where item was donated
     * @return location
     */
    public String getLocation() { return location; }

    /**
     * gives value of item donated
     * @return value
     */
    public double getValue() { return value; }

    /**
     * gives category of item
     * @return category
     */
    public String getCategory() { return category; }

    /**
     * set date of when item was donated
     * @param date when item was donated
     */
    public void setDate(String date) { this.date = date; }

    /**
     * set time of when item was donated
     * @param time time when items was donated
     */
    public void setTime(String time) { this.time = time; }

    /**
     *set location of where item was donated
     * @param location location of where item was donated
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * set value of where item was donated
     * @param value value of item donated
     */
    public void setValue(int value) { this.value = value; }

}
