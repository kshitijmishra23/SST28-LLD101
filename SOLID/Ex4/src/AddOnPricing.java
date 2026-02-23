public class AddOnPricing implements PricingComponent {
    private final Money monthly;

    public AddOnPricing(Money monthly) {
        this.monthly = monthly;
    }

    public Money monthlyFee() {
        return monthly;
    }
}