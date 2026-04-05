/**
 * Abstraction for tax calculation.
 * New tax policies can be added by creating a new implementation.
 */
public interface TaxCalculator {
    double getTaxPercent(String customerType);
}
