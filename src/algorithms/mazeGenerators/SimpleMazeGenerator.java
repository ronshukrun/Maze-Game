package algorithms.mazeGenerators;

//SimpleMazeGenerator generates a simple maze with random paths and open borders.
public class SimpleMazeGenerator extends AMazeGenerator{


    /**
     * Generates a simple maze with the specified number of rows and columns.
     * @param row the number of rows in the maze
     * @param column the number of columns in the maze
     * @return the generated maze
     */
    @Override
    public Maze generate(int row, int column) {
        if ((row <= 1) || (column <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }

        int[][] mazeArr = new int[row][column];
        for(int i = 1; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length - 1; j++)
                mazeArr[i][j] = (int)(Math.random() * 2);
        }
        for(int i = 0; i < mazeArr[0].length; i++)
            mazeArr[0][i] = 0;
        for(int i = 0; i < mazeArr.length; i++)
            mazeArr[i][mazeArr[0].length - 1] = 0;

        Maze newMaze = new Maze(mazeArr);
        return newMaze;
    }
}