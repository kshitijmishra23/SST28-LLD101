public class RoomPricingFactory {

    public static RoomPricing of(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoomPricing();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoomPricing();
            case LegacyRoomTypes.TRIPLE -> new TripleRoomPricing();
            default -> new DeluxeRoomPricing();
        };
    }
}