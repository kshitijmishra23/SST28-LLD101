import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {

        List<PricingComponent> components = new ArrayList<>();

        components.add(RoomPricingFactory.forRoom(req.roomType));
        components.addAll(AddOnPricingFactory.forAddOns(req.addOns));

        Money monthly = new Money(0.0);
        for (PricingComponent pc : components) {
            monthly = monthly.plus(pc.monthlyFee());
        }

        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}