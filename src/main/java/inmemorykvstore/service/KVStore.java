package main.java.inmemorykvstore.service;

import main.java.inmemorykvstore.model.Entry;
import java.util.concurrent.*;

public class KVStore<K, V> {

    private final ConcurrentHashMap<K, Entry<V>> map =
            new ConcurrentHashMap<>();

    // Background thread for removing expired entries
    private final Thread cleaner = new Thread(() -> {

        while (true) {

            try {
                Thread.sleep(1000);
                cleanup();

            } catch (InterruptedException e) {
                break;
            }
        }
    });

    public KVStore() {

        cleaner.setDaemon(true);
        cleaner.start();
    }

    // Add or update a key
    public void put(K key, V value, long ttlMillis) {

        long expiryTime;

        if (ttlMillis <= 0) {
            // Never expires
            expiryTime = Long.MAX_VALUE;
        } else {
            expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        Entry<V> entry = new Entry<>(value, expiryTime);

        map.put(key, entry);
    }

    // Get value
    public V get(K key) {

        Entry<V> entry = map.get(key);

        if (entry == null) {
            return null;
        }

        // Lazy expiry check
        if (entry.isExpired()) {

            // Remove only if this is still the same entry
            map.remove(key, entry);

            return null;
        }

        return entry.getValue();
    }

    // Delete key
    public void delete(K key) {
        map.remove(key);
    }

    // Remove expired entries
private void cleanup() {

    for (K key : map.keySet()) {

        Entry<V> entry = map.get(key);

        if (entry != null && entry.isExpired()) {
            map.remove(key);
        }
    }
}
}