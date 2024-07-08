package com.igor.bs.operation;

import java.util.Objects;

public abstract class Operation {
    private int a;
    private int b;
    Integer result;

    Operation(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public Integer getResult() {
        return this.result;
    }

    public abstract boolean isValid(Integer target);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + a + ", " + b + ")";
    }
}

