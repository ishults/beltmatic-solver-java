package com.igor.bs.operation;

public class Subtract extends Operation {
    public Subtract(int seed, Operation previousOperation) {
        super(seed, previousOperation);
        this.result = previousOperation.getResult() - seed;
    }

    public boolean isValid(Integer target) {
        return result >= 1;
    }
}
