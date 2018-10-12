package donationstation.androidapp.model;

public class DonationItem {
    private String timeStamp;
    private String location;
    private String shortDes;
    private String fullDes;
    private int value;
    private String category;

    public DonationItem(String timeStamp, String location, String shortDes, String fullDes, int value,
                    String category){
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDes = shortDes;
        this.fullDes = fullDes;
        this.value = value;
        this.category = category;
    }

    public String getTimeStamp() { return timeStamp; }

    public String getLocation() { return location; }

    public String getShortDes() { return shortDes; }

    public String getFullDes() { return fullDes; }

    public int getValue() { return value; }

    public String getCategory() { return category; }

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }

    public void setLocation(String location) { this.location = location; }

    public void setShortDes(String shortDes) { this.shortDes = shortDes; }

    public void setFullDes(String fullDes) { this.fullDes = fullDes; }

    public void setValue(int value) { this.value = value; }

    public void setCategory(String category) { this.category = category; }
}
