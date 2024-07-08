package com.igor.bs.operation;

public class Remainder extends Operation {
    public Remainder(int a, int b) {
        super(a, b);
        this.result = b > 0 ? a % b : null;
    }

    public boolean isValid(Integer target) {
        return true;
    }
}
