# Multilevel Parking Lot — LLD Design

## Problem
Design a multilevel parking lot with three slot types (Small, Medium, Large),
multiple entry gates, nearest-slot assignment, vehicle-to-slot overflow,
and slot-type-based billing.

## APIs

| Method | Description |
|---|---|
| `park(vehicle, entryTime, requestedSlotType, entryGateId)` | Parks a vehicle, returns a `ParkingTicket` |
| `status()` | Returns current slot availability by type (total / available / occupied) |
| `exit(ticket, exitTime)` | Processes exit, returns `Bill` with total amount |

## Class Diagram

> Full PlantUML source: [`class-diagram.puml`](class-diagram.puml)
> Paste into [plantuml.com/plantuml](https://www.plantuml.com/plantuml/uml) to render a visual diagram.

```
 ┌─────────────────────────────────────────────────────────────────────────┐
 │                          ENUMS                                         │
 │                                                                        │
 │  ┌──────────────┐       ┌──────────────┐                               │
 │  │ «enum»       │       │ «enum»       │                               │
 │  │ VehicleType  │       │ SlotType     │                               │
 │  │──────────────│       │──────────────│                               │
 │  │ TWO_WHEELER  │       │ SMALL        │                               │
 │  │ CAR          │       │ MEDIUM       │                               │
 │  │ BUS          │       │ LARGE        │                               │
 │  └──────────────┘       └──────────────┘                               │
 └─────────────────────────────────────────────────────────────────────────┘

 ┌─────────────────────────────────────────────────────────────────────────┐
 │                          MODELS                                        │
 │                                                                        │
 │  ┌──────────────────┐  ┌────────────────────┐  ┌──────────────────┐    │
 │  │ Vehicle          │  │ ParkingSlot        │  │ Gate             │    │
 │  │──────────────────│  │────────────────────│  │──────────────────│    │
 │  │ - licensePlate   │  │ - slotNumber : int │  │ - gateNumber:int │    │
 │  │ - type:VehicleType│ │ - type : SlotType  │  │ - floor : int    │    │
 │  │──────────────────│  │ - floor : int      │  │──────────────────│    │
 │  │ + getLicensePlate()│ │ - occupied : bool  │  │ + getGateNumber()│    │
 │  │ + getType()      │  │────────────────────│  │ + getFloor()     │    │
 │  └──────────────────┘  │ + occupy()         │  └──────────────────┘    │
 │                        │ + free()           │                          │
 │                        │ + isOccupied()     │                          │
 │                        └────────────────────┘                          │
 │                                                                        │
 │  ┌──────────────────────────┐  ┌──────────────────────────┐            │
 │  │ ParkingTicket            │  │ Bill                     │            │
 │  │──────────────────────────│  │──────────────────────────│            │
 │  │ - ticketId : String      │  │ - ticket : ParkingTicket │            │
 │  │ - vehicle : Vehicle      │  │ - exitTime : LocalDateTime│           │
 │  │ - slot : ParkingSlot     │  │ - hoursParked : long     │            │
 │  │ - entryTime : LocalDateTime│ │ - totalAmount : int      │           │
 │  │──────────────────────────│  │──────────────────────────│            │
 │  │ + getTicketId()          │  │ + getTotalAmount()       │            │
 │  │ + getVehicle()           │  │ + getHoursParked()       │            │
 │  │ + getSlot()              │  │ + getTicket()            │            │
 │  │ + getEntryTime()         │  │ + getExitTime()          │            │
 │  └──────────────────────────┘  └──────────────────────────┘            │
 └─────────────────────────────────────────────────────────────────────────┘

 ┌─────────────────────────────────────────────────────────────────────────┐
 │                     INTERFACES (Abstractions)                          │
 │                                                                        │
 │  ┌──────────────────────────────────┐  ┌──────────────────────────┐    │
 │  │ «interface»                      │  │ «interface»              │    │
 │  │ SlotAssignmentStrategy           │  │ PricingPolicy            │    │
 │  │──────────────────────────────────│  │──────────────────────────│    │
 │  │ + findSlot(slots, vehicleType,   │  │ + ratePerHour(SlotType)  │    │
 │  │   requestedSlotType, gate)       │  │   : int                  │    │
 │  │   : Optional<ParkingSlot>        │  │                          │    │
 │  └───────────────┬──────────────────┘  └────────────┬─────────────┘    │
 │                  │ implements                       │ implements        │
 │                  ▼                                  ▼                   │
 │  ┌──────────────────────────────────┐  ┌──────────────────────────┐    │
 │  │ NearestSlotStrategy             │  │ FlatRatePricingPolicy    │    │
 │  │──────────────────────────────────│  │──────────────────────────│    │
 │  │ + findSlot(...)                  │  │ - rates:Map<SlotType,Int>│    │
 │  │ - compareByDistance(a, b, gate)  │  │ + ratePerHour(SlotType)  │    │
 │  └──────────────────────────────────┘  └──────────────────────────┘    │
 └─────────────────────────────────────────────────────────────────────────┘

 ┌─────────────────────────────────────────────────────────────────────────┐
 │                       SERVICES                                         │
 │                                                                        │
 │  ┌───────────────────────────────────────────────────────────────┐      │
 │  │ ParkingLot  (Orchestrator)                                   │      │
 │  │──────────────────────────────────────────────────────────────│      │
 │  │ - slots : List<ParkingSlot>                                  │      │
 │  │ - gates : Map<Integer, Gate>                                 │      │
 │  │ - assignmentStrategy : SlotAssignmentStrategy  ◄── DIP      │      │
 │  │ - billingService : BillingService              ◄── DIP      │      │
 │  │ - activeTickets : Map<String, ParkingTicket>                 │      │
 │  │──────────────────────────────────────────────────────────────│      │
 │  │ + park(vehicle, entryTime, slotType, gateId) : ParkingTicket │      │
 │  │ + status() : Map<SlotType, long[]>                           │      │
 │  │ + exit(ticket, exitTime) : Bill                              │      │
 │  └───────────────────────────────────────────────────────────────┘      │
 │                                                                        │
 │  ┌──────────────────────────────────┐  ┌──────────────────────────┐    │
 │  │ BillingService                   │  │ VehicleSlotMapping       │    │
 │  │──────────────────────────────────│  │  (Utility)               │    │
 │  │ - pricingPolicy : PricingPolicy  │  │──────────────────────────│    │
 │  │──────────────────────────────────│  │ + compatibleSlots(       │    │
 │  │ + generateBill(ticket, exitTime) │  │   VehicleType)           │    │
 │  │   : Bill                         │  │   : List<SlotType>       │    │
 │  └──────────────────────────────────┘  └──────────────────────────┘    │
 └─────────────────────────────────────────────────────────────────────────┘

 RELATIONSHIPS:
 ─────────────
 ParkingLot ───uses──▶ SlotAssignmentStrategy (interface)
 ParkingLot ───uses──▶ BillingService
 ParkingLot ◆──has───▶ ParkingSlot (many)
 ParkingLot ◆──has───▶ Gate (many)
 ParkingLot ◆──has───▶ ParkingTicket (active)
 BillingService ──uses──▶ PricingPolicy (interface)
 BillingService ──creates─▶ Bill
 ParkingTicket ──refs──▶ Vehicle
 ParkingTicket ──refs──▶ ParkingSlot
 Bill ──refs──▶ ParkingTicket
 NearestSlotStrategy ..|▶ SlotAssignmentStrategy
 FlatRatePricingPolicy ..|▶ PricingPolicy
 NearestSlotStrategy ──uses──▶ VehicleSlotMapping
```

## SOLID Principles Applied

| Principle | Where |
|---|---|
| **SRP** | `ParkingLot` only coordinates; `BillingService` handles billing; `VehicleSlotMapping` owns compatibility |
| **OCP** | New strategies implement `SlotAssignmentStrategy`; new pricing via `PricingPolicy` — zero changes to existing code |
| **LSP** | Any `PricingPolicy` or `SlotAssignmentStrategy` impl works as a drop-in replacement |
| **ISP** | Interfaces are minimal — one method each |
| **DIP** | `ParkingLot` depends on abstractions (`SlotAssignmentStrategy`, `BillingService`), not concrete classes |

## Design Patterns
- **Strategy** — `SlotAssignmentStrategy` / `NearestSlotStrategy`
- **Composition Root** — all wiring in `App.main()`

## Key Design Decisions
1. **Billing by slot type, not vehicle type** — if a bike parks in a MEDIUM slot, it pays the MEDIUM rate
2. **Overflow** — a smaller vehicle can park in a larger slot if its preferred type is full
3. **Nearest slot** — distance = |slotFloor - gateFloor| * 1000 + slotNumber (floor proximity first, then slot number as tiebreaker)
4. **Gate registry** — gates are looked up by ID inside ParkingLot so the API stays clean

## Build & Run
```bash
cd parking-lot/src
javac com/example/parking/*.java
java com.example.parking.App
```
