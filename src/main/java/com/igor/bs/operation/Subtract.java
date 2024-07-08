package com.igor.bs.operation;

public class Subtract extends Operation {
    public Subtract(int a, int b) {
        super(a, b);
        this.result = a - b;
    }

    public boolean isValid(Integer target) {
        return result >= 1;
    }
}
