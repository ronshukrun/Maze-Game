package algorithms.search;
import java.util.ArrayList;

public class Solution

{
    private ArrayList<AState> AStates ;

    public Solution(ArrayList<AState> AStates)
    {
        if(AStates == null)
        {
            throw new RuntimeException("The ArrayList that supplied is not legal! (null)");
        }
        this.AStates = AStates;
    }


    public ArrayList<AState> getSolutionPath()
    {
        return this.AStates;
    }
}