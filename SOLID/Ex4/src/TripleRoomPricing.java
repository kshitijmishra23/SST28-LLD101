public class TripleRoomPricing implements RoomPricing {
    @Override public boolean supports(int roomType) { return roomType == LegacyRoomTypes.TRIPLE; }
    @Override public double getMonthlyBase() { return 12000.0; }
}
