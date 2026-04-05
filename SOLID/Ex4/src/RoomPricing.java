/**
 * Abstraction for room pricing.
 * Each room type provides its own base monthly fee.
 */
public interface RoomPricing {
    boolean supports(int roomType);
    double getMonthlyBase();
}
