package com.igor.bs.operation;

public class Multiply extends Operation {
    public Multiply(int seed, Operation previousOperation) {
        super(seed, previousOperation);
        this.result = seed * previousOperation.getResult();
    }

    public boolean isValid(Integer target) {
        return getSeed() != 1
                && getPreviousOperation().getResult() != 1
                && result <= target * 1.1; // Overshoot and then subtract if needed. It does hit performance, though
    }
}
