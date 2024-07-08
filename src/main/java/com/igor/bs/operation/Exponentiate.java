package com.igor.bs.operation;

public class Exponentiate extends Operation {
    public Exponentiate(int a, int b) {
        super(a, b);

        try {
            double result = Math.pow(a, b);

           this.result = result > Integer.MAX_VALUE ? null : (int) result;
        } catch (Exception e) {
            this.result = null;
        }
    }

    public boolean isValid(Integer target) {
        return getA() > 1 &&
                getB() > 1 &&
                result != null &&
                result <= target * 1.1; // Overshoot and then subtract if needed. It does hit performance, though
    }
}
