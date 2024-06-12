package algorithms.search;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class describes a searching algorithm that works using the Depth First Search method.
 */
public class DepthFirstSearch extends ASearchingAlgorithm{


    /**
     * Constructor for DepthFirstSearch.
     * Sets the name of the algorithm.
     */
    public DepthFirstSearch() {
        super();
        this.setName("DepthFirstSearch");
    }

    /**
     * Solves an ISearchable problem using the Depth First Search algorithm.
     * @param searchable the problem to be solved
     * @return Solution of the ISearchable problem
     * @throws RuntimeException if the provided ISearchable is null
     */
    //@Override
    public Solution solve(ISearchable searchable) {

        if (searchable == null) {

            throw new RuntimeException("The ISearchable provided is null");
        }

        AState startState = searchable.getStartState();

        AState goalState = searchable.getGoalState();

        Stack<AState> stack = new Stack<>();

        List<AState> sol = new ArrayList<>();

        int visited = 0;

        stack.push(startState);

        searchable.setVisited(startState);
        visited++;

        while (!stack.isEmpty()) {
            AState currentState = stack.pop();
            sol.add(currentState);

            if (currentState.equalsState(goalState)) {
                this.setNodesNum(visited);

                searchable.clearVisited();
                Solution newSol = new Solution(sol);
                return newSol;
            }

            List<AState> possibleMoves = searchable.getAllSuccessors(currentState);
            for (AState move : possibleMoves) {
                if (!move.legalState() || searchable.isVisited(move)) {
                    continue;
                }
                stack.push(move);
                searchable.setVisit(move);
                visitedStates++;
            }
        }

        this.setNodesNum(visitedStates);
        searchable.resetProblem();
        return new Solution(new ArrayList<>()); // No solution found, return empty path
    }
}
