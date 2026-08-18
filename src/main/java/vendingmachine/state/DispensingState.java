package main.java.vendingmachine.state;

import main.java.vendingmachine.model.Item;
import main.java.vendingmachine.model.State;
import main.java.vendingmachine.service.VendingMachine;

public class DispensingState implements State {

    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("Busy...");
    }

    public void selectItem(VendingMachine machine, String code) {
        System.out.println("Busy...");
    }

    public void dispense(VendingMachine machine) {

        Item item = machine.getSelected();

        item.stock--;

        int change = machine.getBalance() - item.price;

        machine.resetBalance();
        machine.setSelected(null);
        machine.setState(machine.IDLE);

        System.out.println(
            "Dispensed " + item.name + ". Change: " + change
        );
    }
}
