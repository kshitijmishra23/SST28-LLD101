public class RoomPricing implements PricingComponent {
    private final Money monthly;

    public RoomPricing(Money monthly) {
        this.monthly = monthly;
    }

    public Money monthlyFee() {
        return monthly;
    }
}