package com.igor.bs.operation;

public class Divide extends Operation {
    public Divide(int a, int b) {
        super(a, b);
        this.result = b > 0 ? a / b : null;
    }

    public boolean isValid(Integer target) {
        return getA() > getB() && result >= target;
    }
}
