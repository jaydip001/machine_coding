package main.java.vendingmachine.state;

import main.java.vendingmachine.model.Item;
import main.java.vendingmachine.model.State;
import main.java.vendingmachine.service.VendingMachine;

public class HasMoneyState implements State {

    public void insertCoin(VendingMachine machine, int amount) {
        machine.addBalance(amount);
        System.out.println("Balance: " + machine.getBalance());
    }

    public void selectItem(VendingMachine machine, String code) {

        Item item = machine.getItem(code);

        if (item == null) {
            System.out.println("Unknown code.");
            return;
        }

        if (item.stock == 0) {
            System.out.println("Out of stock.");
            return;
        }

        if (machine.getBalance() < item.price) {
            System.out.println(
                "Need " + (item.price - machine.getBalance()) + " more."
            );
            return;
        }

        machine.setSelected(item);
        machine.setState(machine.DISPENSING);

        machine.dispense();
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Select an item first.");
    }
}
