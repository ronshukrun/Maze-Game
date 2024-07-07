package algorithms.mazeGenerators;

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
    @Override
    public Maze generate(int rows, int columns) {
        if ((rows <= 1) || (columns <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }
        int[][] mazeArr = new int[rows][columns];
        Maze maze = new Maze(mazeArr);
        return maze;

    }
}