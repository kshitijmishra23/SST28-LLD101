import java.util.*;

public class RoomPricingFactory {

    private static final Map<Integer, Money> ROOM_PRICES = Map.of(
            LegacyRoomTypes.SINGLE, new Money(14000.00),
            LegacyRoomTypes.DOUBLE, new Money(15000.00),
            LegacyRoomTypes.TRIPLE, new Money(12000.00),
            LegacyRoomTypes.DELUXE, new Money(16000.00)
    );

    public static PricingComponent forRoom(int roomType) {
        return new RoomPricing(ROOM_PRICES.getOrDefault(roomType, new Money(16000.00)));
    }
}