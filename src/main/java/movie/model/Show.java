package main.java.movie.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Show {

    private final String id;

    private final Map<String, Seat> seats =
            new ConcurrentHashMap<>();

    public Show(String id, int seatCount) {

        this.id = id;

        for (int i = 1; i <= seatCount; i++) {
            String seatId = "S" + i;
            seats.put(seatId, new Seat(seatId));
        }
    }

    public Seat getSeat(String seatId) {

        Seat seat = seats.get(seatId);

        if (seat == null) {
            throw new IllegalArgumentException(
                    "Seat does not exist: " + seatId
            );
        }

        return seat;
    }

    public String getId() {
        return id;
    }
}