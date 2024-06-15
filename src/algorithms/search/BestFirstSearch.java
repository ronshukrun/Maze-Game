package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch() {
        super();
        this.setName("BestFirstSearch");
    }

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
