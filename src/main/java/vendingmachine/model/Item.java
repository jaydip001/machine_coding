package main.java.vendingmachine.model;

public class Item {
    public final String code;
    public final String name;
    public final int price;
    public int stock;

    public Item(String code, String name, int price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}