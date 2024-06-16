package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BestFirstSearchTest is a unit test class for testing input validation in the BestFirstSearch algorithm.
 * This class ensures that the algorithm handles various edge cases and invalid inputs correctly.
 */
class BestFirstSearchTest {

    //Tests that the solve method throws an IllegalArgumentException when given a null searchable.
    @Test
    void testSolveNullSearchable() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> {
            bfs.solve(null);
        });
    }

     //Tests that checks that solving a maze with valid and invalid start and goal positions works.
    @Test
    void testSolveInvalidStartOrGoalPosition() {
        Maze maze = new Maze(5, 5);
        // Check invalid start position
        assertThrows(IllegalArgumentException.class, () -> {maze.setStartPosition(new Position(-1, -1));});
        // Check invalid goal position
        assertThrows(IllegalArgumentException.class, () -> {maze.setGoalPosition(new Position(5, 5));});
        // Set valid start and goal positions
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(4, 4));
        // Create a searchable maze and solver
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bfs = new BestFirstSearch();
        // Check solving with valid maze configuration
        Solution solution = bfs.solve(searchableMaze);
        // Add assertions to verify the solution if needed
    }

     //Tests that the solve method correctly finds a path in a valid maze.
    @Test
    void testSolveValidMaze() {
        Maze maze = new Maze(5, 5);
        maze.setMaze(0, 1, 0);
        maze.setMaze(0, 2, 0);
        maze.setMaze(1, 2, 0);
        maze.setMaze(2, 2, 0);
        maze.setMaze(3, 2, 0);
        maze.setMaze(4, 2, 0);
        maze.setGoalPosition(new Position(4, 2));
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bfs = new BestFirstSearch();
        Solution solution = bfs.solve(searchableMaze);
        assertNotNull(solution);
        assertEquals(maze.getGoalPosition(), solution.getSolutionPath().get(solution.getSolutionPath().size() - 1).getState());
    }
    //Tests that the getName method returns the correct name of the search algorithm.
    @Test
    void testGetName() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertEquals("BestFirstSearch", bfs.getName());
    }
    /**
     * Tests that the setNumberOfNodesEvaluated method throws a RuntimeException
     * when trying to set the number of nodes evaluated manually.
     */
    @Test
    void testSetNumberOfNodesEvaluatedInvalid() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(RuntimeException.class, () -> {
            bfs.setNumberOfNodesEvaluated(1);
        });
    }
     //Tests that the setName method throws a RuntimeException when trying to set the name to null.
    @Test
    void testSetNameNull() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(RuntimeException.class, () -> {
            bfs.setName(null);
        });
    }
}
