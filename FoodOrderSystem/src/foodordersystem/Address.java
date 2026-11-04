package foodordersystem;

public class Address {

    String street;
    String district;
    String city;

    public Address(String street, String district, String city) {
        this.street = street;
        this.district = district;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Street: " + street + " District: " + district + " City: " + city;
    }
}

