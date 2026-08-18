package main.java.splitwise.model;

public class Split {

    public final String userId;
    public final double amount;

    public Split(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}