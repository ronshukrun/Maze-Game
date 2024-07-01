package algorithms.mazeGenerators;

<<<<<<< HEAD
public class SimpleMazeGenerator extends AMazeGenerator{
    public SimpleMazeGenerator(){}

    /**
     * Creates a simple maze by assigning random values to each cell.
     * Ensures the borders of the maze are open paths (value 0).
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return the simple maze that was created
=======
//SimpleMazeGenerator generates a simple maze with random paths and open borders.
public class SimpleMazeGenerator extends AMazeGenerator{

    public SimpleMazeGenerator(){}

    /**
     * Generates a simple maze with the specified number of rows and columns.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the generated maze
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze simpleMaze = new Maze(rows, columns);
<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        // Fill the maze with random values (0 or 1)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                simpleMaze.setMaze(i, j, Math.random() < 0.5 ? 0 : 1);
            }
        }
<<<<<<< HEAD

        // Set the borders to be open paths (0)
        setBordersToZero(simpleMaze, rows, columns);

=======
        // Set the borders to be open paths (0)
        setBordersToZero(simpleMaze, rows, columns);
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        return simpleMaze;
    }

    /**
<<<<<<< HEAD
     * Sets the borders of the maze to be open paths (value 0).
     * @param maze the maze to modify
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
=======
     * Sets the borders of the maze to be open paths (0).
     * @param maze the maze to modify
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    private void setBordersToZero(Maze maze, int rows, int columns) {
        // Set top and bottom rows to 0
        for (int i = 0; i < columns; i++) {
            maze.setMaze(0, i, 0);
            maze.setMaze(rows - 1, i, 0);
        }
<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        // Set left and right columns to 0
        for (int i = 0; i < rows; i++) {
            maze.setMaze(i, 0, 0);
            maze.setMaze(i, columns - 1, 0);
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
