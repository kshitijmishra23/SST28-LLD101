import java.util.*;

/**
 * Calculates hostel fees by iterating over pluggable room and add-on pricing components.
 * No switch statement needed — adding a new room type or add-on is just creating a new class
 * and adding it to the lists.
 */
public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<RoomPricing> roomPricings;
    private final List<AddOnPricing> addOnPricings;
    private final double defaultRoomBase = 16000.0; // fallback for unknown room types

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;

        // register all known room pricings
        this.roomPricings = new ArrayList<>();
        roomPricings.add(new SingleRoomPricing());
        roomPricings.add(new DoubleRoomPricing());
        roomPricings.add(new TripleRoomPricing());
        roomPricings.add(new DeluxeRoomPricing());

        // register all known add-on pricings
        this.addOnPricings = new ArrayList<>();
        addOnPricings.add(new MessAddOnPricing());
        addOnPricings.add(new LaundryAddOnPricing());
        addOnPricings.add(new GymAddOnPricing());
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        // find the matching room pricing
        double base = defaultRoomBase;
        for (RoomPricing rp : roomPricings) {
            if (rp.supports(req.roomType)) {
                base = rp.getMonthlyBase();
                break;
            }
        }

        // sum up all matching add-on costs
        double addOnTotal = 0.0;
        for (AddOn addon : req.addOns) {
            for (AddOnPricing ap : addOnPricings) {
                if (ap.supports(addon)) {
                    addOnTotal += ap.getMonthlyCost();
                    break;
                }
            }
        }

        return new Money(base + addOnTotal);
    }
}
