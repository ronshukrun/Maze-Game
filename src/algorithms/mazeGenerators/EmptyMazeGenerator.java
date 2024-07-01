package algorithms.mazeGenerators;

<<<<<<< HEAD
public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator() {}

=======
/**
 * EmptyMazeGenerator is a maze generator that creates an empty maze.
 * All cells in the maze are set to 0 (open path).
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator() {}

    /**
     * Generates an empty maze with the specified number of rows and columns.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the generated empty maze
     */
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.setMaze(i, j,0);
<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
            }
        }
        return maze;
    }
}
