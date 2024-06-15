package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    public BreadthFirstSearch() {
        super();
        this.setName("BreadthFirstSearch");
    }

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
