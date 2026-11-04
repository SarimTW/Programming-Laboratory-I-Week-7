package foodordersystem;

public class FoodOrderSystem {
    public static void main(String[] args) {
        
        Customer customer1 = new Customer("John Doe", 5551000, "Main St. No:5", "District A", "City X");
        Customer customer2 = new Customer("Jane Smith", 5552000, "Park Ave. No:12", "District B", "City Y");

        System.out.println("=== CREATING ORDER 1 ===");
        Order order1 = new Order(customer1);
        
        try {
            MenuItem pizza = new MenuItem("Pizza", 150.0, 1);
            MenuItem burger = new MenuItem("Burger", 80.0, 1);

            order1.addItem(pizza);
            order1.addItem(burger);
            order1.addItem(new MenuItem("Burger", 80.0, 1)); 

            order1.updateQuantity("Pizza", 2);

            order1.removeItem("Burger");

            Coupon validCoupon = new Coupon("DISCOUNT10", 10.0, 100.0);
            System.out.println("\n--- Order 1 Checkout (With Applicable Coupon) ---");
            order1.checkout(validCoupon);

        } catch (Exception e) {
            System.out.println("Order 1 Error: " + e.getMessage());
        }

        System.out.println("\n=== CREATING ORDER 2 ===");
        Order order2 = new Order(customer2);
        
        try {
            MenuItem pasta = new MenuItem("Pasta", 200.0, 1);
            order2.addItem(pasta);

            Coupon highMinCoupon = new Coupon("VIP50", 50.0, 500.0);
            System.out.println("\n--- Order 2 Checkout (Coupon Criteria Not Met) ---");
            order2.checkout(highMinCoupon);

        } catch (Exception e) {
            System.out.println("Order 2 Error: " + e.getMessage());
        }

        System.out.println("\n==========================================");
        System.out.println("       CUSTOM EXCEPTION TESTING           ");
        System.out.println("==========================================");

        System.out.println("\n[Test 1] Triggering InvalidQuantityException:");
        try {
            MenuItem invalidItem = new MenuItem("Soda", 30.0, 1);
            invalidItem.setQuantity(0); // Setting 0 or negative quantity
        } catch (InvalidQuantityException e) {
            System.out.println("SUCCESS: Caught InvalidQuantityException -> " + e.getMessage());
        }

        System.out.println("\n[Test 2] Triggering ItemNotFoundException:");
        try {
            order1.removeItem("NonExistingItem"); // Removing an item not in order
        } catch (ItemNotFoundException e) {
            System.out.println("SUCCESS: Caught ItemNotFoundException -> " + e.getMessage());
        }

        System.out.println("\n[Test 3] Triggering MinimumOrderException:");
        try {
            Order lowValueOrder = new Order(customer1);
            lowValueOrder.addItem(new MenuItem("Water", 15.0, 1)); // Subtotal < MIN_ORDER (100 TL)
            Coupon dummyCoupon = new Coupon("NONE", 0, 0);
            lowValueOrder.checkout(dummyCoupon);
        } catch (MinimumOrderException e) {
            System.out.println("SUCCESS: Caught MinimumOrderException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Other Error: " + e.getMessage());
        }

        System.out.println("\n==========================================");
        System.out.println("           STATIC STATISTICS              ");
        System.out.println("==========================================");
        
        System.out.println("Completed Orders Count: " + Order.getCompletedOrders().size());
        System.out.println("Total Revenue: " + Order.getTotalRevenue() + " TL");
    }
}