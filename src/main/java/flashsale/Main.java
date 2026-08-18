package main.java.flashsale;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws Exception {

        InventoryService inventory = new InventoryService(100);

        AtomicInteger sold = new AtomicInteger(0);

        ExecutorService pool = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 1000; i++) {

            pool.execute(() -> {

                if (inventory.tryPurchase()) {
                    sold.incrementAndGet();
                }

            });
        }

        pool.shutdown();

        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Sold: " + sold.get());
        System.out.println("Remaining: " + inventory.remaining());
    }
}
