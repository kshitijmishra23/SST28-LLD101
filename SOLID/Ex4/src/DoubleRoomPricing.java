public class DoubleRoomPricing implements RoomPricing {
    @Override public boolean supports(int roomType) { return roomType == LegacyRoomTypes.DOUBLE; }
    @Override public double getMonthlyBase() { return 15000.0; }
}
