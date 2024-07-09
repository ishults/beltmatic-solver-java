package com.igor.bs;

import com.igor.bs.operation.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Can't be a singleton because of queue, seeds, and visitedNodes
public class SolverService {
    private static final List<Class<? extends Operation>> POSSIBLE_OPERATIONS = List.of(Exponentiate.class, Multiply.class, Add.class, Subtract.class); // Prioritize bigger gains. Division and remainder never seem necessary?
    private List<Integer> seeds = new ArrayList<>();
    private final Queue<Integer> queue = new LinkedList<>();
    private final Map<Integer, Operation> visitedNodes = new HashMap<>();

    SolverService() {}

    public Operation solve(int target, int max) {
        // Also counts for > Integer.MAX_VALUE thanks to integer overflow
        if (target <= 0 || max <= 0 || max > 99) {
           return null;
        }

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
            Integer next = queue.poll();

            generateNextOperations(visitedNodes.get(next), target);
        }

        // Check if we found it
        if (visitedNodes.containsKey(target)) {
            return visitedNodes.get(target);
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

                possibleOperation.setSteps(lastOperation.getSteps() + 1);

                if (visitedNodes.containsKey(result)) {
                    continue;
                }

                queue.add(result);
                visitedNodes.put(result, possibleOperation);

                // Return early if we found it
                if (result == target) {
                    return;
                }
            }
        }
    }

    private static List<Operation> getPossibleOperations(int seed, Operation lastOperation) {
        return POSSIBLE_OPERATIONS.stream()
                .map(operationClass -> {
                    try {
                        return operationClass.getDeclaredConstructor(int.class, Operation.class)
                                .newInstance(seed, lastOperation);
                    } catch (Exception e) {
                        System.err.println("Failed to instantiate operation: " + operationClass + ". Exception: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
