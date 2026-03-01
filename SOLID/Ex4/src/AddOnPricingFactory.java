public class AddOnPricingFactory {

    public static AddOnPricing of(AddOn addOn) {
        return switch (addOn) {
            case MESS -> new MessPricing();
            case LAUNDRY -> new LaundryPricing();
            case GYM -> new GymPricing();
        };
    }
}
