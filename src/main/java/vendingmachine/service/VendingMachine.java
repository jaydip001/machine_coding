package main.java.vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import main.java.vendingmachine.model.Item;
import main.java.vendingmachine.model.State;
import main.java.vendingmachine.state.DispensingState;
import main.java.vendingmachine.state.HasMoneyState;
import main.java.vendingmachine.state.IdleState;

public class VendingMachine {

    public final State IDLE = new IdleState();
    public final State HAS_MONEY = new HasMoneyState();
    public final State DISPENSING = new DispensingState();

    private State state = IDLE;

    private int balance = 0;

    private Item selected;

    private final Map<String, Item> inventory = new HashMap<>();

    // Public APIs

    public void insertCoin(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive"
            );
        }

        state.insertCoin(this, amount);
    }

    public void selectItem(String code) {
        state.selectItem(this, code);
    }

    public void dispense() {
        state.dispense(this);
    }

    public void stockItem(Item item) {
        inventory.put(item.code, item);
    }

    // Helpers used by states

    public void setState(State state) {
        this.state = state;
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void resetBalance() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public Item getItem(String code) {
        return inventory.get(code);
    }

    public Item getSelected() {
        return selected;
    }

    public void setSelected(Item item) {
        this.selected = item;
    }
}
