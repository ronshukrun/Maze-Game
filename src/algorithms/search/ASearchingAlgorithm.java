package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private String name;
    private int nodesNum;


    public ASearchingAlgorithm() {
        this.name = "ASearchingAlgorithm";
        this.nodesNum = -1;
    }

    @Override
    public String getName() {return this.name;}

    @Override
    public int getNumberOfNodesEvaluated() {return this.nodesNum;}

    @Override
    public void setName(String name) {
        if (name == null) {
            throw new RuntimeException("The name provided is null");
        }
        this.name = name;
    }
    @Override
    public void setNumberOfNodesEvaluated(int nodesNum) {
        if (!(nodesNum > 1)) {
            throw new RuntimeException("The number of nodes must be bigger then 2");
        }
        this.nodesNum = nodesNum;
    }

    public static Solution getSolutionPath(AState startState, AState goalState) {
        if (startState == null || goalState == null) {
            throw new IllegalArgumentException("Start state or goal state is null.");
        }

        ArrayList<AState> solutionPath = new ArrayList<>();
        AState currentState = goalState;

        while (currentState != null) {
            solutionPath.add(0, currentState);
            if (currentState.equalsState(startState)) {
                break;
            }
            currentState = currentState.getPrevState();
        }

        return new Solution(solutionPath);
    }
}