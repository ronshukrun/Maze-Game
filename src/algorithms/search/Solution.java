package algorithms.search;
import java.util.ArrayList;
<<<<<<< HEAD

public class Solution

{
    private ArrayList<AState> AStates ;

    /**
     * Constructor gets an ArrayList of AState
     * presenting the solution path
     * and sets it as the AStates field
     * @param AStates the solution path array
=======
/**
 * Solution represents the solution to a search problem. It stores the path of states
 * from the start state to the goal state.
 */
public class Solution {

    private ArrayList<AState> AStates ;

    /**
     * Constructs a Solution with the given list of states.
     * @param AStates the list of states representing the solution path
     * @throws RuntimeException if the provided list is null
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    public Solution(ArrayList<AState> AStates)
    {
        if(AStates == null)
        {
            throw new RuntimeException("The ArrayList that supplied is not legal! (null)");
        }
        this.AStates = AStates;
    }

    /**
<<<<<<< HEAD
     * @return the Solution Path of a problem (ArrayList<AState>)
=======
     * Gets the solution path as a list of states.
     * @return the list of states representing the solution path
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    public ArrayList<AState> getSolutionPath()
    {
        return this.AStates;
    }
}