package com.igor.bs.operation;

public class Remainder extends Operation {
    public Remainder(int seed, Operation previousOperation) {
        super(seed, previousOperation);
        this.result = previousOperation.getResult() > 0 ? seed % previousOperation.getResult() : null;
    }

    public boolean isValid(Integer target) {
        return true;
    }
}
