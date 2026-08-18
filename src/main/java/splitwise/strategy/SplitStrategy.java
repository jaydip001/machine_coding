package main.java.splitwise.strategy;

import java.util.List;

import main.java.splitwise.model.Split;

public interface SplitStrategy {

    List<Split> computeSplits(
            double total,
            List<String> users,
            List<Double> values
    );
}