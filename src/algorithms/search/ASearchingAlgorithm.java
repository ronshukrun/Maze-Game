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
    public static Solution restoreSolutionPath(AState startState, AState thisState) {
        if ((startState == null) || (thisState == null)) {
            throw new RuntimeException("arguments is not valid - null");
        }
        ArrayList<AState> SolutionPath = new ArrayList<AState>();
        boolean flag = false;

        while (!flag) {
            SolutionPath.add(0, thisState);
            if (thisState.equalsState(startState)) {
                flag = true;
                continue;
            }
            thisState = thisState.getPrevState();
        }
        return new Solution(SolutionPath);
    }

}