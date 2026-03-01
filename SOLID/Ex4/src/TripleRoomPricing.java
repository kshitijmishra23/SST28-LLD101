public class TripleRoomPricing implements RoomPricing {
    public Money monthly() { 
        return new Money(12000.0); 
    }
}