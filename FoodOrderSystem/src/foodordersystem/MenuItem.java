package foodordersystem;


public class MenuItem {

    String name;
    double price;
    int quantity;

    public MenuItem(String name, double price, int quantity) {
        this.name = name;
        if (price>0){
            this.price = price;
        }
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price>0){
        this.price = price;
        }
    }

    public void setQuantity(int quantity) throws InvalidQuantityException {
        if (quantity<=0){
            throw new InvalidQuantityException("Invalid Quantity Value!");
        }
        else {
            this.quantity = quantity; 
        }
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Price: " + price + " Quantity: " + quantity;
    }
}




