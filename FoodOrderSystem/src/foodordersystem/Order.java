package foodordersystem;

import java.util.ArrayList;

public class Order {
    
    static int nextOrderNumber = 1001;

    Customer customer;
    private ArrayList<MenuItem> items = new ArrayList<>();
    double price;
    int orderNumber;

    public Order(Customer customer) {
        this.customer = customer;
        this.orderNumber = nextOrderNumber;
    }
    
    

    public void addItem(MenuItem item) throws InvalidQuantityException {
        boolean s=true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(item.name)){
                items.get(i).setQuantity(items.get(i).getQuantity() + 1);
                s=false;
            }
        }
        if (s)
            items.add(item);
    }

    public void removeItem (String name) throws ItemNotFoundException {
        boolean s=true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)){
                items.remove(i);
                s=false;
            }
        }
        if (s) {
            throw new ItemNotFoundException("Item not found!");
        }
    }

    public void updateQuantity(String name, int quantity) throws ItemNotFoundException {
        boolean s=true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)){
                items.get(i).quantity=quantity;
                s=false;
            }
        }
        if (s) {
            throw new ItemNotFoundException("Item not found!");
        } 
    }

    private double getSubtotal() {
        double subTotal = 0;
        for (int i = 0; i < items.size(); i++) {
            subTotal += items.get(i).price*items.get(i).quantity;
        }
        return subTotal;
    }

    public ArrayList<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public void applyCoupon(Coupon coupon) {
    double subtotal = getSubtotal();
    if (coupon != null && subtotal >= coupon.minSubtotal) {
        price = subtotal * (1 - coupon.discountRate / 100.0);
    } else {
        price = subtotal; 
    }
}

    static final double MIN_ORDER = 100;
    static final double DELIVERY_FEE = 20;
    static ArrayList<Order> completedOrders=new ArrayList<>();
    static double totalRevenue=0;

    public static ArrayList<Order> getCompletedOrders() {
        return completedOrders;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }
    boolean isCheckedOut=false;
    public double checkout(Coupon coupon) throws MinimumOrderException {
        if (isCheckedOut) {
            System.out.println("This order has already been completed!");
            return 0;
        }
        double total;
        if ( getSubtotal() < MIN_ORDER) {
            throw new MinimumOrderException("The fee is less than the minimum order price!");
        }
        this.applyCoupon(coupon);
        total=this.price+DELIVERY_FEE;
        
        System.out.println("Order Number: "+orderNumber);
        System.out.println("Customer name: "+customer.name);
        System.out.println("Address: "+customer.address);
        System.out.println("Items: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        System.out.println("Subtotal: "+getSubtotal());
        System.out.println("Coupon Discount (%): "+coupon.discountRate);
        System.out.println("Delivery Fee: "+DELIVERY_FEE);
        System.out.println("Total: "+total);
        
        nextOrderNumber++;
        totalRevenue+=total;
        completedOrders.add(this);
        isCheckedOut=true;
        
        return total;
    }
}
