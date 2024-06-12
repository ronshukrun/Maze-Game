package algorithms.mazeGenerators;

/**
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
        long currTime = System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        long time = end-currTime;
        return time;
    }
}
