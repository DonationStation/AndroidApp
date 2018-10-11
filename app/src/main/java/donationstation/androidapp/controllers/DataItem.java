package donationstation.androidapp.controllers;

class DataItem {
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

    public DataItem(int key, String name, String latitude, String longitude, String address, String city, String state,
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

    public String toString() {
        return name + " " + key ;
    }
    public String getWebsite() { return website; }
    public String getPhone() { return phone; }
    public String getType() { return type; }
    public String getZip() { return zip; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getLongitude() { return longitude; }
    public String getLatitude() { return latitude; }
    public String getName() {return name; }
    public int getKey() {return key; }
}
