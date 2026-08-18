package main.java.parking.strategy;


import java.util.Map;

import main.java.parking.model.VehicleType;

public class HourlyFeeStrategy implements FeeStrategy {

    private static final Map<VehicleType, Double> RATE = Map.of(
            VehicleType.BIKE, 10.0,
            VehicleType.CAR, 30.0,
            VehicleType.TRUCK, 50.0
    );

    @Override
    public double calculate(VehicleType type, long hours) {
        return Math.max(1, hours) * RATE.get(type);
    }

}
