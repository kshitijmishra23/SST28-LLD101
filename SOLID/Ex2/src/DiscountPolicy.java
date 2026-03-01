public interface DiscountPolicy {
    double discountAmount(String customerType, double subtotal, int distinctLines);
}
