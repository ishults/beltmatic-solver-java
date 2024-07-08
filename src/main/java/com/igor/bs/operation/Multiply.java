package com.igor.bs.operation;

public class Multiply extends Operation {
    public Multiply(int a, int b) {
        super(a, b);
        this.result = a * b;
    }

    public boolean isValid(Integer target) {
        return getA() != 1
                && getB() != 1
                && result <= target * 1.1; // Overshoot and then subtract if needed. It does hit performance, though
    }
}
