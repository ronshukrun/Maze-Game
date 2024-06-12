package algorithms.search;

public interface ISearchingAlgorithm {

    String getAlgorithmName();
    void setAlgorithmName(String algorithmName);
    int getNodesNum();
    void setNodesNum(int nodesNum);
    Solution Solution (ISearchable searchable );

}
