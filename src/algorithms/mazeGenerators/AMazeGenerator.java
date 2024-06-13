package algorithms.mazeGenerators;

public abstract class  AMazeGenerator implements IMazeGenerator
{

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long currTime = System.currentTimeMillis();
        generate(rows, columns);
        long end = System.currentTimeMillis();
        long time = end-currTime;
        return time;
    }
}
