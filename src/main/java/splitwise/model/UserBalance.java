package main.java.splitwise.model;

public class UserBalance {

    public final String userId;
    public double balance;

    public UserBalance(String userId) {
        this.userId = userId;
        this.balance = 0;
    }
}
