package com.igor.bs.operation;

public class Extract extends Operation {
    public Extract (int seed) {
        super(seed, null);
        this.result = seed;
    }

    public boolean isValid(Integer target) {
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + getSeed() + ")";
    }
}
