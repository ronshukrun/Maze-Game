package algorithms.search;

import java.util.ArrayList;

/**
 * An abstract class representing a searching algorithm.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private static final String LIBRARY_NAME = "algorithms.search";
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

    public String getName() {return this.name;}

    public int getNodesNum() {return this.nodesNum;}


    public void setName(String name) {
        if (name == null) {
            throw new RuntimeException("The name provided is null");
        }
        this.name = name;
    }

    public void setNodesNum(int nodesNum) {
        if (!(nodesNum > 1)) {
            throw new RuntimeException("The number of nodes must be bigger then 2");
        }
        this.nodesNum = nodesNum;
    }

//    /**
//     * Restores the solution path found between two states.
//     * @param startState the starting state of the problem
//     * @param thisState  the goal state of the problem
//     * @return the solution path (Solution)
//     * @throws RuntimeException if one or both of the supplied arguments are null
//     */
//    public static Solution restoreSolutionPath(AState startState, AState thisState) {
//        if (startState == null || thisState == null) {
//            throw new RuntimeException("One or more of the supplied arguments is not legal (null)");
//        }
//        ArrayList<AState> solutionPath = new ArrayList<>();
//
//        while (true) {
//            solutionPath.add(0, thisState);
//            if (thisState.compStates(startState)) {
//                break;
//            }
//            thisState = thisState.getPrevState();
//        }
//        return new Solution(solutionPath);
//    }
}