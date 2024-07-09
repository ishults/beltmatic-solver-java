package com.igor.bs.operation;

public class Divide extends Operation {
    public Divide(int seed, Operation previousOperation) {
        super(seed, previousOperation);
        this.result = previousOperation.getResult() > 0 ? seed / previousOperation.getResult() : null;
    }

    public boolean isValid(Integer target) {
        return getSeed() > getPreviousOperation().getResult() && result >= target;
    }
}
