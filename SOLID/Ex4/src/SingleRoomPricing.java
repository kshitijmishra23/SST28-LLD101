public class SingleRoomPricing implements RoomPricing {
    @Override public boolean supports(int roomType) { return roomType == LegacyRoomTypes.SINGLE; }
    @Override public double getMonthlyBase() { return 14000.0; }
}
