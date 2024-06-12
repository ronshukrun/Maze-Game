//package algorithms.search;
//
//import java.util.ArrayList;
//import java.util.PriorityQueue;
//
//public class BestFirstSearch extends ASearchingAlgorithm {
//
//    public BestFirstSearch() {
//        super();
//        this.setName("BestFirstSearch");
//    }
//
//    /**
//     * Solves an ISearchable problem using the Best First Search algorithm.
//     * It starts from the initial state and explores states based on a priority queue.
//     * @param searchable the problem to solve
//     * @return Solution of the searchable problem
//     */
//    @Override
//    public Solution solve(ISearchable searchable) {
//        if (searchable == null) {
//            throw new IllegalArgumentException("The ISearchable provided is null.");
//        }
//
//        PriorityQueue<AState> priorityQueue = new PriorityQueue<>();
//        AState startState = searchable.getStartState();
//        AState goalState = searchable.getGoalState();
//
//        priorityQueue.add(startState);
//        searchable.setVisited(startState);
//        int visitedStates = 1;
//
//        while (!priorityQueue.isEmpty()) {
//            AState currentState = priorityQueue.poll();
//
//            if (currentState.equalsState(goalState)) {
//                this.setNumberOfNodesEvaluated(visitedStates);
//                return getSolutionPath(searchable.getStartState(), currentState);
//            }
//
//            ArrayList<AState> successors = searchable.getValidStates(currentState);
//            for (AState successor : successors) {
//                if (!successor.validState() || searchable.isVisited(successor)) {
//                    continue;
//                }
//
//                successor.setPrevState(currentState);
//                priorityQueue.add(successor);
//                searchable.setVisited(successor);
//                visitedStates++;
//            }
//        }
//
//        this.setNumberOfNodesEvaluated(visitedStates);
//        searchable.clearVisited();
//        return null; // return null if no solution found
//    }
//}
package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Constructor for BestFirstSearch.
     * Sets the name of the algorithm.
     */
    public BestFirstSearch() {
        super();
        this.setName("BestFirstSearch");
    }

    /**
     * Solves an ISearchable problem using the Best First Search algorithm.
     * @param searchable the problem to solve
     * @return Solution of the searchable problem
     * @throws IllegalArgumentException if the provided ISearchable is null
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("The ISearchable provided is null.");
        }

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        // Comparator to order the states by cost
        PriorityQueue<AState> priorityQueue = new PriorityQueue<>(new Comparator<AState>() {
            @Override
            public int compare(AState s1, AState s2) {
                return Double.compare(s1.getCost(), s2.getCost());
            }
        });

        ArrayList<AState> solutionPath = new ArrayList<>();
        int visitedStates = 0;

        startState.setCost(0);
        priorityQueue.add(startState);
        searchable.setVisited(startState);
        visitedStates++;

        while (!priorityQueue.isEmpty()) {
            AState currentState = priorityQueue.poll();
            solutionPath.add(currentState);

            if (currentState.equalsState(goalState)) {
                this.setNumberOfNodesEvaluated(visitedStates);
                searchable.clearVisited();
                return new Solution(solutionPath);
            }

            ArrayList<AState> successors = searchable.getValidStates(currentState);
            for (AState successor : successors) {
                if (!successor.validState() || searchable.isVisited(successor)) {
                    continue;
                }
                successor.setPrevState(currentState);
                double newCost = currentState.getCost() + calculateCost(currentState, successor);
                successor.setCost(newCost);
                priorityQueue.add(successor);
                searchable.setVisited(successor);
                visitedStates++;
            }
        }

        this.setNumberOfNodesEvaluated(visitedStates);
        searchable.clearVisited();
        return null; // No solution found
    }

    /**
     * Calculates the cost to move from one state to another.
     * @param fromState the state from which we are moving
     * @param toState the state to which we are moving
     * @return the cost of the move
     */
    private double calculateCost(AState fromState, AState toState) {
        // Implement the logic to calculate the cost based on your specific problem.
        // For instance, you can check if the move is diagonal or not and return the appropriate cost.
        // Assuming that for diagonal moves the cost is sqrt(2) and for non-diagonal moves it's 1
        Position fromPos = (Position) fromState.getState();
        Position toPos = (Position) toState.getState();

        int rowDiff = Math.abs(fromPos.getRowIndex() - toPos.getRowIndex());
        int colDiff = Math.abs(fromPos.getColumnIndex() - toPos.getColumnIndex());

        if (rowDiff == 1 && colDiff == 1) {
            return Math.sqrt(2); // Diagonal move
        } else {
            return 1; // Non-diagonal move
        }
    }
}
