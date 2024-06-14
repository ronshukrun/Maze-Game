package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator() {}

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.setMaze(i, j,0);

            }
        }
        return maze;
    }
}
