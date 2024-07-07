package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Solution represents the solution to a search problem. It stores the path of states
 * from the start state to the goal state.
 */
public class Solution implements Serializable {
    //private static final long serialVersionUID = 1L; // for serialization compatibility
    private ArrayList<AState> AStates ;

    /**
     * Constructs a Solution with the given list of states.
     * @param AStates the list of states representing the solution path
     * @throws RuntimeException if the provided list is null
     */
    public Solution(ArrayList<AState> AStates)
    {
        if(AStates == null)
        {
            throw new RuntimeException("The ArrayList is not valid - null");
        }
        this.AStates = AStates;
    }

    /**
     * Gets the solution path as a list of states.
     * @return the list of states representing the solution path
     */
    public ArrayList<AState> getSolutionPath()
    {
        return this.AStates;
    }
}