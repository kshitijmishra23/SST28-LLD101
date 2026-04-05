public class LaundryAddOnPricing implements AddOnPricing {
    @Override public boolean supports(AddOn addOn) { return addOn == AddOn.LAUNDRY; }
    @Override public double getMonthlyCost() { return 500.0; }
}
