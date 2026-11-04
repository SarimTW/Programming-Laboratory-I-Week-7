package foodordersystem;

public class Coupon {

    String code;
    double discountRate;
    double minSubtotal;

    public Coupon(String code, double discountRate, double minSubtotal) {
        this.code=code;
        this.discountRate = discountRate;
        this.minSubtotal = minSubtotal;
    }
    
}
