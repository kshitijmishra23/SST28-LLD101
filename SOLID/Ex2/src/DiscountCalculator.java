/**
 * Abstraction for discount calculation.
 */
public interface DiscountCalculator {
    double getDiscount(String customerType, double subtotal, int distinctItems);
}
