package main.java.parking.model;

import java.util.UUID;

public class Ticket {

    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTimeMs;
    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTimeMs = System.currentTimeMillis();
;
    }
    public String getId() {
      return id;
    }
    public Vehicle getVehicle() {
      return vehicle;
    }
    public ParkingSpot getSpot() {
      return spot;
    }
    public long getEntryTimeMs() {
      return entryTimeMs;
    }

    
  }