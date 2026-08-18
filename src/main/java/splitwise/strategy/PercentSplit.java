package main.java.splitwise.strategy;

import java.util.*;

import main.java.splitwise.model.Split;

public class PercentSplit implements SplitStrategy {

    @Override
    public List<Split> computeSplits(
            double total,
            List<String> users,
            List<Double> values) {

        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        if (Math.abs(sum - 100.0) > 0.01) {
            throw new IllegalArgumentException(
                    "Percentages must add up to 100"
            );
        }

        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            double amount =
                    total * values.get(i) / 100.0;

            splits.add(
                    new Split(users.get(i), amount)
            );
        }

        return splits;
    }
}
