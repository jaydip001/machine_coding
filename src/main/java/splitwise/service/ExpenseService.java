package main.java.splitwise.service;

import java.util.*;

import main.java.splitwise.model.Split;
import main.java.splitwise.model.UserBalance;
import main.java.splitwise.strategy.SplitStrategy;

public class ExpenseService {

    private final Map<String, UserBalance> users =
            new HashMap<>();

    public void addUser(String userId) {
        users.put(userId, new UserBalance(userId));
    }

    public synchronized void addExpense(
            String paidBy,
            double total,
            List<String> participants,
            SplitStrategy strategy,
            List<Double> values) {

        List<Split> splits =
                strategy.computeSplits(
                        total,
                        participants,
                        values
                );

        // Payer gets credit for the full amount paid
        users.get(paidBy).balance += total;

        // Every participant owes their share
        for (Split split : splits) {
            users.get(split.userId).balance -= split.amount;
        }
    }

    public synchronized void settle() {

      PriorityQueue<UserBalance> debtors =
        new PriorityQueue<>(
                (a, b) -> Double.compare(a.balance, b.balance)
        );

      PriorityQueue<UserBalance> creditors =
        new PriorityQueue<>(
                (a, b) -> Double.compare(b.balance, a.balance)
        );

        for (UserBalance user : users.values()) {

            if (user.balance < -0.01) {
                debtors.offer(user);
            }
            else if (user.balance > 0.01) {
                creditors.offer(user);
            }
        }

        while (!debtors.isEmpty()
                && !creditors.isEmpty()) {

            UserBalance debtor = debtors.poll();
            UserBalance creditor = creditors.poll();

            double payment =
                    Math.min(
                            -debtor.balance,
                            creditor.balance
                    );

            System.out.printf(
                    "%s pays %s: %.2f%n",
                    debtor.userId,
                    creditor.userId,
                    payment
            );

            debtor.balance += payment;
            creditor.balance -= payment;

            if (debtor.balance < -0.01) {
                debtors.offer(debtor);
            }

            if (creditor.balance > 0.01) {
                creditors.offer(creditor);
            }
        }
    }
}