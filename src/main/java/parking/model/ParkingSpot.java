package main.java.parking.model;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {

    private final String id;
    private final VehicleType type;

    private final AtomicBoolean occupied = new AtomicBoolean(false);
    private volatile Vehicle vehicle;

    public ParkingSpot(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean tryOccupy(Vehicle vehicle) {
        if (occupied.compareAndSet(false, true)) {
            this.vehicle = vehicle;
            return true;
        }

        return false;
    }

    public void free() {
        this.vehicle = null;
        occupied.set(false);
    }

    public boolean isOccupied() {
        return occupied.get();
    }
}
