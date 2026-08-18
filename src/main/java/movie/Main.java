package main.java.movie;



import java.util.List;

import main.java.movie.model.Show;
import main.java.movie.service.BookingService;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Show show = new Show(
                "Interstellar-9PM",
                20
        );

        BookingService service =
                new BookingService();

        // Alice wants S1, S2
        Thread alice = new Thread(() -> {

            boolean result =
                    service.holdSeats(
                            show,
                            List.of("S1", "S2"),
                            "alice"
                    );

            System.out.println(
                    "Alice hold: " + result
            );
        });

        // Bob wants S2, S3
        Thread bob = new Thread(() -> {

            boolean result =
                    service.holdSeats(
                            show,
                            List.of("S2", "S3"),
                            "bob"
                    );

            System.out.println(
                    "Bob hold: " + result
            );
        });

        alice.start();
        bob.start();

        alice.join();
        bob.join();

        // Alice tries to confirm
        boolean confirmed =
                service.confirmBooking(
                        show,
                        List.of("S1", "S2"),
                        "alice"
                );

        System.out.println(
                "Alice booking confirmed: "
                        + confirmed
        );
    }
}
