package algorithms.search;

public interface ISearchingAlgorithm {

    String getName();
    void setName(String algorithmName);
    int getNumberOfNodesEvaluated();
    void setNumberOfNodesEvaluated(int nodesNum);
    Solution solve(ISearchable searchable );

}
