package main.java.splitwise;

import java.util.List;

import main.java.splitwise.service.ExpenseService;
import main.java.splitwise.strategy.EqualSplit;
import main.java.splitwise.strategy.ExactSplit;

public class Main {

    public static void main(String[] args) {

        ExpenseService service =
                new ExpenseService();

        service.addUser("alice");
        service.addUser("bob");
        service.addUser("cara");

        // Alice pays 900.
        // Equal split: 300 each.
        service.addExpense(
                "alice",
                900,
                List.of("alice", "bob", "cara"),
                new EqualSplit(),
                null
        );

        // Bob pays 300.
        // Alice owes 120, Bob owes 180.
        service.addExpense(
                "bob",
                300,
                List.of("alice", "bob"),
                new ExactSplit(),
                List.of(120.0, 180.0)
        );

        System.out.println("Settlement:");

        service.settle();
    }
}
