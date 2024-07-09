package com.igor.bs;

import com.igor.bs.operation.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Not making this a singleton cause it's spun up and killed. Otherwise the cache can just be primed and that's no fun ★
public class SolverService {
    private int target;
    private List<Integer> seeds = new ArrayList<>();
    private final Queue<Integer> queue = new LinkedList<>();
    private final Map<Integer, Operation> visitedNodes = new HashMap<>();

    SolverService() {} // Could potentially put target/max/seeds here

    public Operation solve(int target, int max) {
        // Also counts for > Integer.MAX_VALUE thanks to integer overflow
        if (target <= 0 || max <= 0 || max > 99) {
           return null;
        }

        this.target = target;

        // Create the possible seeds/extraction targets
        seeds = IntStream.rangeClosed(1, max)
                .filter(num -> num != 10) // 10 is not an extraction target in the game!
                .boxed()
                .toList();

        // Prime the queue and map
        for (int seed : seeds) {
            queue.add(seed);
            visitedNodes.put(seed, new Extract(seed));
        }

        // Iterate through the queue
        while (!queue.isEmpty()) {
            // Check if we found it
            if (visitedNodes.containsKey(target)) {
                return visitedNodes.get(target);
            }

            Integer next = queue.poll();

            generateNextOperations(visitedNodes.get(next), target);
        }

        // Oh no
        return null;
    }

    private void generateNextOperations(Operation lastOperation, int target) {
        for (int seed : seeds) {
            List<Operation> possibleOperations = getPossibleOperations(seed, lastOperation);

            for (Operation possibleOperation : possibleOperations) {
                if (!possibleOperation.isValid(target)) {
                    continue;
                }

                Integer result = possibleOperation.getResult();

                if (result == null
                        || result <= 0)
                {
                    continue;
                }

                if (visitedNodes.containsKey(result)) {
                    continue;
                }

                queue.add(result);
                visitedNodes.put(result, possibleOperation);
            }
        }
    }

    private static List<Operation> getPossibleOperations(int result, Operation lastOperation) {
        return List.of(
                new Exponentiate(result, lastOperation),
                new Multiply(result, lastOperation),
                new Add(result, lastOperation),
                new Subtract(result, lastOperation)
        ); // Division and remainder do not seem to ever be used?
    }
}
