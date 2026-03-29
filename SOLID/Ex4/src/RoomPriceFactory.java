public class RoomPriceFactory {
    public static RoomPrice getPricing(int roomType) {

        switch (roomType) {

            case LegacyRoomTypes.SINGLE:
                return new SingleRoomPrice();

            case LegacyRoomTypes.DOUBLE:
                return new DoubleRoomPrice();

            case LegacyRoomTypes.TRIPLE:
                return new TripleRoomPrice();

            default:
                throw new IllegalArgumentException("Unknown room type");
        }
    }
}
