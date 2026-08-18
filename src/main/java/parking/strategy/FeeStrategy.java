package main.java.parking.strategy;

import main.java.parking.model.VehicleType;

public interface FeeStrategy {

    double calculate(VehicleType type, long hours);
}
