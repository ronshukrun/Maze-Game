//package algorithms.search;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class BreadthFirstSearch extends ASearchingAlgorithm {
//
//    public BreadthFirstSearch() {
//        super();
//        this.setName("BreadthFirstSearch");
//    }
//
//    /**
//     * This method solves an ISearchable problem using the Breadth First Search algorithm.
//     * It starts from the initial state and explores the nearest states first using a queue.
//     * @param searchable the problem to solve
//     * @return Solution of the searchable problem
//     */
//    @Override
//    public Solution solve(ISearchable searchable) {
//        if (searchable == null) {
//            throw new IllegalArgumentException("The ISearchable provided is null.");
//        }
//
//        AState startState = searchable.getStartState();
//        AState goalState = searchable.getGoalState();
//        Queue<AState> queue = new LinkedList<>();
//        queue.add(startState);
//        searchable.setVisited(startState);
//        int visitedStates = 1;
//        while (!queue.isEmpty()) {
//            AState currentState = queue.poll();
//            if (currentState.equalsState(goalState)) {
//                this.setNumberOfNodesEvaluated(visitedStates);
//                return getSolutionPath(startState, currentState);
//            }
//            ArrayList<AState> successors = searchable.getValidStates(currentState);
//            for (AState successor : successors) {
//                if (!searchable.isVisited(successor)) {
//                    successor.setPrevState(currentState);
//                    queue.add(successor);
//                    searchable.setVisited(successor);
//                    visitedStates++;
//                }
//            }
//        }
//
//        this.setNumberOfNodesEvaluated(visitedStates);
//        searchable.clearVisited();
//        return null; // return null if no solution found
//    }
//
//    /**
//     * Generates the solution path from the start state to the goal state.
//     * @param startState the starting state
//     * @param goalState the goal state
//     * @return Solution object containing the path
//     */
//    public static Solution getSolutionPath(AState startState, AState goalState) {
//        ArrayList<AState> path = new ArrayList<>();
//        AState currentState = goalState;
//
//        while (currentState != null) {
//            path.add(0, currentState);
//            currentState = currentState.getPrevState();
//        }
//
//        return new Solution(path);
//    }
//}
package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    /**
     * Constructor for BreadthFirstSearch.
     * Sets the name of the algorithm.
     */
    public BreadthFirstSearch() {
        super();
        this.setName("BreadthFirstSearch");
    }

    /**
     * Solves an ISearchable problem using the Breadth First Search algorithm.
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
