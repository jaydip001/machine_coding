package main.java.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import main.java.parking.model.ParkingSpot;
import main.java.parking.model.Ticket;
import main.java.parking.model.Vehicle;
import main.java.parking.model.VehicleType;
import main.java.parking.strategy.FeeStrategy;
import main.java.parking.strategy.HourlyFeeStrategy;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    private ParkingLot() {
    }

    private final Map<VehicleType, List<ParkingSpot>> spotsByType =
            new ConcurrentHashMap<>();

    private final Map<String, Ticket> activeTickets =
            new ConcurrentHashMap<>();

    private final FeeStrategy feeStrategy =
            new HourlyFeeStrategy();
    
   public void addSpot(ParkingSpot spot) {
    if (!spotsByType.containsKey(spot.getType())) {
        spotsByType.put(spot.getType(), new ArrayList<>());
    }

    spotsByType.get(spot.getType()).add(spot);

   } 

    public Ticket park(Vehicle vehicle) {

        List<ParkingSpot> spots =
                spotsByType.getOrDefault(vehicle.getType(), List.of());

        for (ParkingSpot spot : spots) {

            if (spot.tryOccupy(vehicle)) {

                Ticket ticket = new Ticket(vehicle, spot);

                activeTickets.put(ticket.getId(), ticket);

                return ticket;
            }
        }

        throw new IllegalStateException(
                "No free spot for " + vehicle.getType()
        );
    }

    public double unpark(String ticketId) {

        Ticket ticket = activeTickets.remove(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Invalid or already-used ticket"
            );
        }

        long durationMs =
                System.currentTimeMillis() - ticket.getEntryTimeMs();

        long hours = Math.max(
                1,
                (long) Math.ceil(durationMs / 3_600_000.0)
        );

        ticket.getSpot().free();

        return feeStrategy.calculate(
                ticket.getVehicle().getType(),
                hours
        );
    }
}
