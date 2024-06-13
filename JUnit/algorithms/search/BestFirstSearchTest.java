package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void testSolveNullSearchable() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> {
            bfs.solve(null);
        });
    }

    @Test
    void testSolveSingleNodeMaze() {
        Maze singleNodeMaze = new Maze(1, 1);
        SearchableMaze searchableMaze = new SearchableMaze(singleNodeMaze);
        BestFirstSearch bfs = new BestFirstSearch();
        Solution solution = bfs.solve(searchableMaze);

        assertNotNull(solution);
        assertEquals(1, solution.getSolutionPath().size());
        assertEquals(singleNodeMaze.getStartPosition(), solution.getSolutionPath().get(0).getState());
    }

    @Test
    void testSolveDisconnectedMaze() {
        Maze maze = new Maze(3, 3);
        maze.setMaze(0, 1, 1);
        maze.setMaze(1, 1, 1);
        maze.setMaze(2, 1, 1);

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bfs = new BestFirstSearch();
        Solution solution = bfs.solve(searchableMaze);

        assertNull(solution);  // Expecting null because there's no path from start to goal
    }


    @Test
    void testSolveInvalidStartOrGoalPosition() {
        Maze maze = new Maze(5, 5);

        // Check invalid start position
        assertThrows(IllegalArgumentException.class, () -> {
            maze.setStartPosition(new Position(-1, -1));
        });

        // Check invalid goal position
        assertThrows(IllegalArgumentException.class, () -> {
            maze.setGoalPosition(new Position(5, 5));
        });

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

    @Test
    void testGetName() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertEquals("BestFirstSearch", bfs.getName());
    }

    @Test
    void testSetNumberOfNodesEvaluatedInvalid() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(RuntimeException.class, () -> {
            bfs.setNumberOfNodesEvaluated(1);
        });
    }

    @Test
    void testSetNameNull() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertThrows(RuntimeException.class, () -> {
            bfs.setName(null);
        });
    }
}
