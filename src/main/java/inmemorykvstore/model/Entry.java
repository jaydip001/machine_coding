package main.java.inmemorykvstore.model;

public class Entry<V> {

    private final V value;
    private final long expiryTime;

    public Entry(V value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
