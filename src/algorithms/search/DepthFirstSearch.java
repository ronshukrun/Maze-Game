<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
package algorithms.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
<<<<<<< HEAD

public class DepthFirstSearch extends ASearchingAlgorithm {

    /**
     * Constructor for DepthFirstSearch.
     * Sets the name of the algorithm.
=======
/**
 * DepthFirstSearch is an implementation of the Depth-First Search algorithm.
 * This algorithm explores as far as possible along each branch before backtracking.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * Constructor for DepthFirstSearch.
     * Sets the name of the search algorithm to "DepthFirstSearch".
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    public DepthFirstSearch() {
        super();
        this.setName("DepthFirstSearch");
    }

    /**
<<<<<<< HEAD
     * Solves an ISearchable problem using the Depth First Search algorithm.
     * @param searchable the problem to be solved
     * @return Solution of the ISearchable problem
     * @throws RuntimeException if the provided ISearchable is null
=======
     * Solves the given ISearchable problem using the Depth-First Search algorithm.
     *
     * @param searchable the ISearchable problem to be solved
     * @return a Solution object containing the path from the start state to the goal state,
     *         or an empty solution if no solution is found
     * @throws IllegalArgumentException if the provided ISearchable is null
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("The ISearchable provided is null.");
        }

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();
        Stack<AState> stack = new Stack<>();
        ArrayList<AState> solutionPath = new ArrayList<>();
        int visitedStates = 0;

        stack.push(startState);
        searchable.setVisited(startState);
        visitedStates++;

        while (!stack.isEmpty()) {
            AState currentState = stack.pop();
            solutionPath.add(currentState);

            if (currentState.equalsState(goalState)) {
                this.setNumberOfNodesEvaluated(visitedStates);
                searchable.clearVisited();
                return new Solution(solutionPath);
            }

            List<AState> successors = searchable.getValidStates(currentState);
            for (AState successor : successors) {
                if (!successor.validState() || searchable.isVisited(successor)) {
                    continue;
                }
                successor.setPrevState(currentState);
                stack.push(successor);
                searchable.setVisited(successor);
                visitedStates++;
            }
        }

        this.setNumberOfNodesEvaluated(visitedStates);
        searchable.clearVisited();
        return new Solution(new ArrayList<>()); // No solution found
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
