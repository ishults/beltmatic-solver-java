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

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Operation other = (Operation) obj;

        // Compare all relevant fields
        return (Objects.equals(this.getA(), other.getA()) &&
                    Objects.equals(this.getB(), other.getB()) ||
                    Objects.equals(this.getA(), other.getB()) &&
                    Objects.equals(this.getB(), other.getA())) &&
                Objects.equals(this.getResult(), other.getResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, result, this.getClass().getSimpleName());
    }
}

