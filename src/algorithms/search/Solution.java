package algorithms.search;
import java.util.ArrayList;

public class Solution

{
    private ArrayList<AState> AStates ;

    /**
     * Constructor gets an ArrayList of AState
     * presenting the solution path
     * and sets it as the AStates field
     * @param AStates the solution path array
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
     * @return the Solution Path of a problem (ArrayList<AState>)
     */
    public ArrayList<AState> getSolutionPath()
    {
        return this.AStates;
    }
}