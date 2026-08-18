package main.java.vendingmachine.model;
import main.java.vendingmachine.service.VendingMachine;

public interface State {

    void insertCoin(VendingMachine machine, int amount);

    void selectItem(VendingMachine machine, String code);

    void dispense(VendingMachine machine);
}