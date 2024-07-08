package com.igor.bs.operation;

public class Extract extends Operation {
    public Extract (int a, int b) {
        super(a, b);
        this.result = a;
    }

    public boolean isValid(Integer target) {
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + getA() + ")";
    }
}
