package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * BreadthFirstSearch is an implementation of the Breadth-First Search algorithm.
 * This algorithm explores all the nodes at the present depth level before moving on to the nodes at the next depth level.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {
    /**
     * Constructor for BreadthFirstSearch.
     * Sets the name of the search algorithm to "BreadthFirstSearch".
     */
    public BreadthFirstSearch() {
        super();
        this.setName("BreadthFirstSearch");
    }
    /**
     * Solves the given ISearchable problem using the Breadth-First Search algorithm.
     *
     * @param searchable the ISearchable problem to be solved
     * @return a Solution object containing the path from the start state to the goal state,
     *         or null if no solution is found
     * @throws IllegalArgumentException if the provided ISearchable is null
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("The ISearchable provided is null.");
        }

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();
        Queue<AState> queue = new LinkedList<>();
        ArrayList<AState> solutionPath = new ArrayList<>();
        int visitedStates = 0;

        queue.add(startState);
        searchable.setVisited(startState);
        visitedStates++;

        while (!queue.isEmpty()) {
            AState currentState = queue.poll();
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
                queue.add(successor);
                searchable.setVisited(successor);
                visitedStates++;
            }
        }

        this.setNumberOfNodesEvaluated(visitedStates);
        searchable.clearVisited();
        return null; // No solution found
    }
}
