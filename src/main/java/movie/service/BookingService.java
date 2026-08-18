package main.java.movie.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.movie.model.Seat;
import main.java.movie.model.Show;

public class BookingService {

    private static final long HOLD_TIME =
            5 * 60 * 1000; // 5 minutes

    public boolean holdSeats(
            Show show,
            List<String> seatIds,
            String userId) {

        // Always lock seats in same order
        List<String> ordered =
                new ArrayList<>(seatIds);

        Collections.sort(ordered);

        List<Seat> heldSeats = new ArrayList<>();

        for (String seatId : ordered) {

            Seat seat = show.getSeat(seatId);

            boolean success =
                    seat.tryHold(userId, HOLD_TIME);

            if (!success) {

                // Rollback already held seats
                for (Seat heldSeat : heldSeats) {
                    heldSeat.release(userId);
                }

                return false;
            }

            heldSeats.add(seat);
        }

        return true;
    }

    public boolean confirmBooking(
            Show show,
            List<String> seatIds,
            String userId) {

        for (String seatId : seatIds) {

            Seat seat = show.getSeat(seatId);

            if (!seat.confirm(userId)) {
                return false;
            }
        }

        return true;
    }

    public void releaseSeats(
            Show show,
            List<String> seatIds,
            String userId) {

        for (String seatId : seatIds) {
            show.getSeat(seatId).release(userId);
        }
    }
}