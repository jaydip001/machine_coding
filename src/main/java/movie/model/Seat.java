package main.java.movie.model;

public class Seat {

    private final String id;

    private SeatStatus status = SeatStatus.AVAILABLE;
    private String heldByUser;
    private long holdExpiryMs;

    public Seat(String id) {
        this.id = id;
    }

    public synchronized boolean tryHold(String userId, long holdMillis) {

        // Release expired hold first
        releaseIfExpired();

        if (status != SeatStatus.AVAILABLE) {
            return false;
        }

        status = SeatStatus.HELD;
        heldByUser = userId;
        holdExpiryMs = System.currentTimeMillis() + holdMillis;

        return true;
    }

    public synchronized boolean confirm(String userId) {

        releaseIfExpired();

        if (status != SeatStatus.HELD) {
            return false;
        }

        if (!userId.equals(heldByUser)) {
            return false;
        }

        status = SeatStatus.BOOKED;

        return true;
    }

    public synchronized void release(String userId) {

        if (status == SeatStatus.HELD &&
                userId.equals(heldByUser)) {

            status = SeatStatus.AVAILABLE;
            heldByUser = null;
        }
    }

    private void releaseIfExpired() {

        if (status == SeatStatus.HELD &&
                System.currentTimeMillis() > holdExpiryMs) {

            status = SeatStatus.AVAILABLE;
            heldByUser = null;
        }
    }

    public String getId() {
        return id;
    }

    public synchronized SeatStatus getStatus() {
        return status;
    }
}
