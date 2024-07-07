package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest
{
    private BestFirstSearch bfs = new BestFirstSearch();

    @Test
    public void solve() throws Exception
    {
        boolean flag = false;
        try
        {
            bfs.solve(null);
        }
        catch (RuntimeException e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void getName() throws Exception
    {
        assertEquals("BestFirstSearch", bfs.getName(), "failure - The name of the Algorithm is invalid");
    }

    @Test
    public void setName() throws Exception
    {
        bfs.setName("bfs");
        assertEquals("bfs", bfs.getName(), "failure - The name of the Algorithm is invalid");
    }

    @Test
    public void getNumberOfNodesEvaluated() throws Exception
    {
        assertEquals(-1, bfs.getNumberOfNodesEvaluated(), "failure - The value of the EvaluatedNodes is invalid");
        MyMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(10,10);
        SearchableMaze sMaze = new SearchableMaze(maze);
        bfs.solve(sMaze);
        assertNotEquals(-1, bfs.getNumberOfNodesEvaluated(), "failure - The value of the EvaluatedNodes is invalid");
    }

    @Test
    public void setNumberOfNodesEvaluated() throws Exception
    {
        bfs.setNumberOfNodesEvaluated(100);
        assertEquals(100, bfs.getNumberOfNodesEvaluated(), "failure - The value of the EvaluatedNodes is invalid");
    }
}