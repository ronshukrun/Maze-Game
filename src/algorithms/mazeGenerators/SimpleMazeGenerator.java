package algorithms.mazeGenerators;

//SimpleMazeGenerator generates a simple maze with random paths and open borders.
public class SimpleMazeGenerator extends AMazeGenerator{

    public SimpleMazeGenerator(){}

    /**
     * Generates a simple maze with the specified number of rows and columns.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the generated maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze simpleMaze = new Maze(rows, columns);
        // Fill the maze with random values (0 or 1)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                simpleMaze.setMaze(i, j, Math.random() < 0.5 ? 0 : 1);
            }
        }
        // Set the borders to be open paths (0)
        setBordersToZero(simpleMaze, rows, columns);
        return simpleMaze;
    }

    /**
     * Sets the borders of the maze to be open paths (0).
     * @param maze the maze to modify
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     */
    private void setBordersToZero(Maze maze, int rows, int columns) {
        // Set top and bottom rows to 0
        for (int i = 0; i < columns; i++) {
            maze.setMaze(0, i, 0);
            maze.setMaze(rows - 1, i, 0);
        }
        // Set left and right columns to 0
        for (int i = 0; i < rows; i++) {
            maze.setMaze(i, 0, 0);
            maze.setMaze(i, columns - 1, 0);
        }
    }
}