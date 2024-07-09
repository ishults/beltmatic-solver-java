package com.igor.bs.operation;

public abstract class Operation {
    private final int seed;
    private final Operation previousOperation;
    Integer result;

    Operation(int seed, Operation previousOperation) {
        this.seed = seed;
        this.previousOperation = previousOperation;
    }

    public int getSeed() {
        return seed;
    }

    public Operation getPreviousOperation() {
        return previousOperation;
    }

    public Integer getResult() {
        return this.result;
    }

    public abstract boolean isValid(Integer target);

    @Override
    public String toString() {
        return previousOperation.toString() + ", " + this.getClass().getSimpleName() + "(" + seed + ")=" + result;
    }
}

