package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    // gets a AState, and returns all the Possible valid moves in the specific problem
    ArrayList<AState> getValidStates(AState state);

    ArrayList<AState> getPriorityStates(ArrayList<AState> states);

    MazeState getStartState();
    MazeState getGoalState();
    boolean isVisited(AState state);
    void setVisited(AState state);
    void clearVisited();

}
