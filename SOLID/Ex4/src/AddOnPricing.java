/**
 * Abstraction for add-on pricing.
 * Each add-on provides its own monthly cost.
 */
public interface AddOnPricing {
    boolean supports(AddOn addOn);
    double getMonthlyCost();
}
