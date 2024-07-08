package com.igor.bs.operation;

public class Add extends Operation {
    public Add(int a, int b) {
        super(a, b);
        this.result = a + b;
    }

    public boolean isValid(Integer target) {
        return result <= target;
    }
}
