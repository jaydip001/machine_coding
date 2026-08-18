package main.java.flashsale;

import java.util.concurrent.atomic.AtomicInteger;

class InventoryService {

    private final AtomicInteger stock;

    InventoryService(int initialStock) {
        this.stock = new AtomicInteger(initialStock);
    }

    public boolean tryPurchase() {

        while (true) {

            int current = stock.get();

            if (current <= 0) {
                return false;
            }

            if (stock.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }

    public int remaining() {
        return stock.get();
    }
}
