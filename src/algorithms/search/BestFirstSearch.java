package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

}


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
