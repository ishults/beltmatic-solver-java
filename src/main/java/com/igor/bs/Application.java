package com.igor.bs;

public class Application {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Application <target> <max>");
            System.exit(1);
        }

        int target = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);

        if (target <= 0 || target <= 0) {
            System.err.println("Error: target must be greater than 0 and less than Integer.MAX_VALUE");
            System.exit(1);
        }

        if (max <= 0 || max > 99) { // Going over 99 hits performance too much
            System.err.println("Error: max must be greater than 0 and less than 100");
            System.exit(1);
        }

        SolverService solverService = new SolverService();

        System.out.println("The solution is " + solverService.solve(target, max));
    }
}
