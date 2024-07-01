package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
<<<<<<< HEAD

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Constructor for BestFirstSearch.
     * Sets the name of the algorithm.
=======
/**
 * BestFirstSearch is an implementation of the Best-First Search algorithm
 * which extends the BreadthFirstSearch class. This algorithm uses a priority queue
 * to explore the most promising nodes based on their cost, calculated using a heuristic.
 */
public class BestFirstSearch extends BreadthFirstSearch {
    /**
     * Constructor for BestFirstSearch.
     * Sets the name of the search algorithm to "BestFirstSearch".
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    public BestFirstSearch() {
        super();
        this.setName("BestFirstSearch");
    }
<<<<<<< HEAD

    /**
     * Solves an ISearchable problem using the Best First Search algorithm.
     * @param searchable the problem to solve
     * @return Solution of the searchable problem
=======
    /**
     * Solves the given ISearchable problem using the Best-First Search algorithm.
     *
     * @param searchable the ISearchable problem to be solved
     * @return a Solution object containing the path from the start state to the goal state, or null if no solution is found
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     * @throws IllegalArgumentException if the provided ISearchable is null
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("The ISearchable provided is null.");
        }

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        if (startState.equalsState(goalState)) {
            ArrayList<AState> solutionPath = new ArrayList<>();
            solutionPath.add(startState);
            return new Solution(solutionPath);
        }

        // Comparator to order the states by cost
        PriorityQueue<AState> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(AState::getCost));
        ArrayList<AState> solutionPath = new ArrayList<>();
        int visitedStates = 0;

        startState.setCost(0);
        priorityQueue.add(startState);
        searchable.setVisited(startState);
        visitedStates++;

        while (!priorityQueue.isEmpty()) {
            AState currentState = priorityQueue.poll();
            if (currentState.equalsState(goalState)) {
                // Build the solution path by backtracking
                while (currentState != null) {
                    solutionPath.add(0, currentState);
                    currentState = currentState.getPrevState();
                }
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
<<<<<<< HEAD
     * Calculates the cost to move from one state to another.
     * @param fromState the state from which we are moving
     * @param toState the state to which we are moving
     * @return the cost of the move
=======
     * Calculates the cost between two states.
     *
     * @param fromState the state from which the cost is calculated
     * @param toState the state to which the cost is calculated
     * @return the cost between the two states
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    private double calculateCost(AState fromState, AState toState) {
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
