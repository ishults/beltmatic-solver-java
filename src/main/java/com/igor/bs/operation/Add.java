package com.igor.bs.operation;

public class Add extends Operation {
    public Add(int seed, Operation previousOperation) {
        super(seed, previousOperation);
        this.result = seed + previousOperation.getResult();
    }

    public boolean isValid(Integer target) {
        return result <= target;
    }
}
