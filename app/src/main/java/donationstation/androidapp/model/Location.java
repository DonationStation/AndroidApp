package donationstation.androidapp.model;

/**
 * Class to represent a Location
 */
public class Location {
    private int key;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    /**
     * empty constructor to create a location
     */
    public Location() {

    }

    /**
     * creates a location item
     * @param key the location's key
     * @param name the location's name
     * @param latitude the location's latitude
     * @param longitude the locations' longitude
     * @param address the location's address
     * @param city the locations's city
     * @param state the location's state
     * @param zip the location's zip
     * @param type the location's type
     * @param phone the location's phone
     * @param website the location's website
     */
    public Location(int key, String name, String latitude, String longitude,
                    String address, String city, String state,
                    String zip, String type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    /**
     * the string representation of the name and key
     * @return the string representation
     */
    public String toString() {
        return name + " " + key ;
    }

    /**
     * phone number of location
     * @return phone number
     */
    public String getPhone() { return phone; }

    /**
     * type of location
     * @return location type
     */
    public String getType() { return type; }

    /**
     * name of location
     * @return name
     */
    public String getName() {return name; }

    /**
     * location's key
     * @return key
     */
    public int getKey() {return key; }
}
