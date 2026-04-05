package com.example.parking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        // ======== Composition Root (wiring) ========
        PricingPolicy pricing = new FlatRatePricingPolicy(Map.of(
                SlotType.SMALL,  10,
                SlotType.MEDIUM, 20,
                SlotType.LARGE,  50
        ));
        BillingService billing = new BillingService(pricing);
        SlotAssignmentStrategy strategy = new NearestSlotStrategy();

        // two floors, 8 slots total
        List<ParkingSlot> slots = List.of(
                new ParkingSlot(1, SlotType.SMALL,  0),
                new ParkingSlot(2, SlotType.SMALL,  0),
                new ParkingSlot(3, SlotType.MEDIUM, 0),
                new ParkingSlot(4, SlotType.MEDIUM, 0),
                new ParkingSlot(5, SlotType.LARGE,  0),
                new ParkingSlot(6, SlotType.SMALL,  1),
                new ParkingSlot(7, SlotType.MEDIUM, 1),
                new ParkingSlot(8, SlotType.LARGE,  1)
        );

        // two entry gates
        List<Gate> gates = List.of(
                new Gate(1, 0),   // ground floor
                new Gate(2, 1)    // first floor
        );

        ParkingLot lot = new ParkingLot(slots, gates, strategy, billing);

        // ======== Show initial status ========
        System.out.println("=== Initial Status ===");
        printStatus(lot);

        // ======== park() demos ========
        System.out.println("\n=== Parking Vehicles ===");

        Vehicle bike = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        ParkingTicket t1 = lot.park(bike,
                LocalDateTime.of(2026, 3, 23, 10, 0),
                SlotType.SMALL, 1);
        System.out.println("Bike parked  -> " + t1.getTicketId()
                + ", " + t1.getSlot());

        Vehicle car = new Vehicle("KA-02-5678", VehicleType.CAR);
        ParkingTicket t2 = lot.park(car,
                LocalDateTime.of(2026, 3, 23, 10, 15),
                SlotType.MEDIUM, 2);
        System.out.println("Car parked   -> " + t2.getTicketId()
                + ", " + t2.getSlot());

        Vehicle bus = new Vehicle("KA-03-9999", VehicleType.BUS);
        ParkingTicket t3 = lot.park(bus,
                LocalDateTime.of(2026, 3, 23, 11, 0),
                SlotType.LARGE, 1);
        System.out.println("Bus parked   -> " + t3.getTicketId()
                + ", " + t3.getSlot());

        // ======== status() after parking ========
        System.out.println("\n=== Status After Parking ===");
        printStatus(lot);

        // ======== exit() demos ========
        System.out.println("\n=== Vehicle Exits ===");

        Bill b1 = lot.exit(t1, LocalDateTime.of(2026, 3, 23, 12, 30));
        System.out.println("Bike exited  -> " + b1);

        Bill b2 = lot.exit(t2, LocalDateTime.of(2026, 3, 23, 14, 15));
        System.out.println("Car exited   -> " + b2);

        Bill b3 = lot.exit(t3, LocalDateTime.of(2026, 3, 23, 12, 0));
        System.out.println("Bus exited   -> " + b3);

        // ======== status() after exits ========
        System.out.println("\n=== Status After Exits ===");
        printStatus(lot);

        // ======== Overflow demo: bike gets a MEDIUM slot ========
        System.out.println("\n=== Overflow Demo ===");
        lot.park(new Vehicle("KA-04-1111", VehicleType.TWO_WHEELER),
                LocalDateTime.of(2026, 3, 23, 13, 0), SlotType.SMALL, 1);
        lot.park(new Vehicle("KA-04-2222", VehicleType.TWO_WHEELER),
                LocalDateTime.of(2026, 3, 23, 13, 0), SlotType.SMALL, 1);
        lot.park(new Vehicle("KA-04-3333", VehicleType.TWO_WHEELER),
                LocalDateTime.of(2026, 3, 23, 13, 0), SlotType.SMALL, 1);

        // all 3 SMALL slots taken; this bike overflows to MEDIUM
        Vehicle bike4 = new Vehicle("KA-04-4444", VehicleType.TWO_WHEELER);
        ParkingTicket t4 = lot.park(bike4,
                LocalDateTime.of(2026, 3, 23, 13, 5),
                SlotType.SMALL, 1);
        System.out.println("Bike overflow -> " + t4.getSlot()
                + " (billed at " + t4.getSlot().getType() + " rate)");

        System.out.println("\n=== Final Status ===");
        printStatus(lot);
    }

    private static void printStatus(ParkingLot lot) {
        Map<SlotType, long[]> status = lot.status();
        System.out.printf("  %-8s  Total  Available  Occupied%n", "Type");
        for (var entry : status.entrySet()) {
            long[] counts = entry.getValue();
            System.out.printf("  %-8s  %5d  %9d  %8d%n",
                    entry.getKey(), counts[0], counts[1], counts[2]);
        }
    }
}
