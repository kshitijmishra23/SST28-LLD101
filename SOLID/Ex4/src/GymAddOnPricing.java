public class GymAddOnPricing implements AddOnPricing {
    @Override public boolean supports(AddOn addOn) { return addOn == AddOn.GYM; }
    @Override public double getMonthlyCost() { return 300.0; }
}
