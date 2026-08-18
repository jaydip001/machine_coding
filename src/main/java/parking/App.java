package main.java.parking;

import main.java.parking.model.ParkingSpot;
import main.java.parking.model.Ticket;
import main.java.parking.model.Vehicle;
import main.java.parking.model.VehicleType;

public class App {

    public static void main(String[] args) {

        ParkingLot lot = ParkingLot.getInstance();

        lot.addSpot(
                new ParkingSpot("C1", VehicleType.CAR)
        );

        lot.addSpot(
                new ParkingSpot("C2", VehicleType.CAR)
        );

        Vehicle car1 =
                new Vehicle("KA-01-1234", VehicleType.CAR);

        Ticket ticket =
                lot.park(car1);

        System.out.println(
                "Parked at: " + ticket.getSpot().getId()
        );

        double fee =
                lot.unpark(ticket.getId());

        System.out.println(
                "Fee: Rs. " + fee
        );

        lot.park(
                new Vehicle("KA-02-9999", VehicleType.CAR)
        );

        lot.park(
                new Vehicle("KA-03-0007", VehicleType.CAR)
        );

        try {
            lot.park(
                    new Vehicle("KA-04-1111", VehicleType.CAR)
            );
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
