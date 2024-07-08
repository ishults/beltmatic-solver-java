package com.igor.bs;

import com.igor.bs.operation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Can't be a singleton because of queue, seeds, and visitedNodes
public class SolverService {
    private static final List<Class<? extends Operation>> POSSIBLE_OPERATIONS = List.of(Exponentiate.class, Multiply.class, Add.class, Subtract.class); // Prioritize bigger gains. Division and remainder never seem necessary?
    private List<Integer> seeds = new ArrayList<>();
    private final Queue<Integer> queue = new LinkedList<>();
    private final Map<Integer, List<Operation>> visitedNodes = new HashMap<>();

    SolverService() {}

    public List<Operation> solve(int target, int max) {
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
            visitedNodes.put(seed, Arrays.asList(new Extract(seed, 0)));
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

    private void generateNextOperations(List<Operation> operations, int target) {
        Operation lastOperation = operations.getLast();

        for (int seed : seeds) {
            List<Operation> possibleOperations = getPossibleOperations(lastOperation.getResult(), seed);

            for (Operation possibleOperation : possibleOperations) {
                if (!possibleOperation.isValid(target)) {
                    continue;
                }

                Integer result = possibleOperation.getResult();

                if (result == null
                        || result <= 0
                        || visitedNodes.containsKey(result))
                {
                    continue;
                }

                List<Operation> operationsCopy = new ArrayList<>(operations);
                operationsCopy.add(possibleOperation);

                queue.add(result);
                visitedNodes.put(result, operationsCopy);

                // Return early if we found it
                if (result == target) {
                    return;
                }
            }
        }
    }

    private static List<Operation> getPossibleOperations(int result, int seed) {
        return POSSIBLE_OPERATIONS.stream()
                .map(operationClass -> {
                    try {
                        return operationClass.getDeclaredConstructor(int.class, int.class)
                                .newInstance(result, seed);
                    } catch (Exception e) {
                        System.err.println("Failed to instantiate operation: " + operationClass + ". Exception: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
