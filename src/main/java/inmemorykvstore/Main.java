package main.java.inmemorykvstore;

import main.java.inmemorykvstore.service.KVStore;

public class Main {

    public static void main(String[] args) throws Exception {

        KVStore<String, String> store = new KVStore<>();

        // TTL = 1 second
        store.put(
                "otp:jaydip",
                "482913",
                1000
        );

        System.out.println(
                "Immediately: " + store.get("otp:jaydip")
        );

        // Wait for 1.2 seconds
        Thread.sleep(1200);

        System.out.println(
                "After 1.2 seconds: " + store.get("otp:jaydip")
        );

        // TTL <= 0 means never expire
        store.put(
                "name",
                "Jaydip",
                0
        );

        System.out.println(
                "Name: " + store.get("name")
        );

        // Delete
        store.delete("name");

        System.out.println(
                "After delete: " + store.get("name")
        );
    }
}