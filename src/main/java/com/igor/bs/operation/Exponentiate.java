package com.igor.bs.operation;

public class Exponentiate extends Operation {
    public Exponentiate(int seed, Operation previousOperation) {
        super(seed, previousOperation);

        try {
            double result = Math.pow(previousOperation.getResult(), seed);

            this.result = result > Integer.MAX_VALUE ? null : (int) result;
        } catch (Exception e) {
            this.result = null;
        }
    }

    public boolean isValid(Integer target) {
        return getSeed() > 1 &&
                getPreviousOperation().getResult() > 1 &&
                result != null &&
                result <= target * 1.1; // Overshoot and then subtract if needed. It does hit performance, though
    }
}
