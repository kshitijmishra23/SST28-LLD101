public class DiscountRules implements DiscountCalculator {
    @Override
    public double getDiscount(String customerType, double subtotal, int distinctItems) {
        if ("student".equalsIgnoreCase(customerType)) {
            if (subtotal >= 180.0) return 10.0;
            return 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            if (distinctItems >= 3) return 15.0;
            return 5.0;
        }
        return 0.0;
    }
}
