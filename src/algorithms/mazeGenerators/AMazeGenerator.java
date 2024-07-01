package algorithms.mazeGenerators;

/**
<<<<<<< HEAD
 * the AMazeGenerator class is for creating some type of maze
 */
public abstract class  AMazeGenerator implements IMazeGenerator
{

/**
 * measure the time of creating maze
 * @param rows number of rows in the maze
 * @param columns number of columns in the maze
 * @return the time took to create the maze
 */

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {//  to implement
=======
 * AMazeGenerator is an abstract class that implements the IMazeGenerator interface.
 * It provides a default implementation to measure the time taken to generate a maze.
 */
public abstract class  AMazeGenerator implements IMazeGenerator
{
    /**
     * Measures the time taken to generate a maze with the specified number of rows and columns.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the time taken to generate the maze in milliseconds
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        long currTime = System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        long time = end-currTime;
        return time;
    }
}
