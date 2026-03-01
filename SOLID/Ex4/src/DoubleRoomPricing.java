public class DoubleRoomPricing implements RoomPricing {
    public Money monthly() { 
        return new Money(15000.0); 
    }
}
