package main.java.splitwise.strategy;

import java.util.*;

import main.java.splitwise.model.Split;

public class EqualSplit implements SplitStrategy {

    @Override
    public List<Split> computeSplits(
            double total,
            List<String> users,
            List<Double> values) {

        double share =
                Math.round(total * 100.0 / users.size()) / 100.0;

        List<Split> splits = new ArrayList<>();

        for (String user : users) {
            splits.add(new Split(user, share));
        }

        return splits;
    }
}
