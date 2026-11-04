package foodordersystem;

public class Customer {

    String name;
    int phone;
    Address address;

    public Customer(String name, int phone, String street, String district, String city) {
        this.name = name;
        this.phone = phone;
        this.address = new Address(street, district, city);
    }
}
