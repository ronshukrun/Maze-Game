package algorithms.search;

import java.util.ArrayList;

/**
 * An abstract class representing a searching algorithm.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private String name;
    private int nodesNum;

    /**
     * Constructor for the abstract class ASearchingAlgorithm.
     * Initializes the algorithm's name and sets the number of evaluated nodes to -1.
     */
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

    /**
     * Restores the solution path found between two states.
     * @param startState the starting state of the problem
     * @param goalState the goal state of the problem
     * @return the solution path (Solution)
     * @throws RuntimeException if one or both of the supplied arguments are null
     */
    //restoreSolutionPath
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