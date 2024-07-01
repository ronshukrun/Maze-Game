package algorithms.search;

public interface ISearchingAlgorithm {

    String getName();
<<<<<<< HEAD
    void setName(String algorithmName);
    int getNumberOfNodesEvaluated();
    void setNumberOfNodesEvaluated(int nodesNum);
=======

    void setName(String algorithmName);

    int getNumberOfNodesEvaluated();

    void setNumberOfNodesEvaluated(int nodesNum);

>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    Solution solve(ISearchable searchable );

}
