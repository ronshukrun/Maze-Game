package algorithms.search;

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

}