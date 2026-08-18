package main.java.splitwise.strategy;

import java.util.*;

import main.java.splitwise.model.Split;

public class ExactSplit implements SplitStrategy {

    @Override
    public List<Split> computeSplits(
            double total,
            List<String> users,
            List<Double> values) {

        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        if (Math.abs(sum - total) > 0.01) {
            throw new IllegalArgumentException(
                    "Exact amounts must add up to total"
            );
        }

        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            splits.add(
                    new Split(users.get(i), values.get(i))
            );
        }

        return splits;
    }
}
