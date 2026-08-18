package main.java.vendingmachine.state;

import main.java.vendingmachine.model.State;
import main.java.vendingmachine.service.VendingMachine;

public class IdleState implements State {

    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        machine.addBalance(amount);
        machine.setState(machine.HAS_MONEY);

        System.out.println("Accepted. Balance: " + machine.getBalance());
    }

    @Override
    public void selectItem(VendingMachine machine, String code) {
        System.out.println("Insert coins first.");
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Nothing to dispense.");
    }
}
