package main.java.vendingmachine;

import main.java.vendingmachine.model.Item;
import main.java.vendingmachine.service.VendingMachine;

public class Main {

    public static void main(String[] args) {

        VendingMachine vm = new VendingMachine();

        vm.stockItem(
            new Item("A1", "Chips", 20, 2)
        );

        vm.selectItem("A1");

        vm.insertCoin(10);

        vm.selectItem("A1");

        vm.insertCoin(20);

        vm.selectItem("A1");
    }
}