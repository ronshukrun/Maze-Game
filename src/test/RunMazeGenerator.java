package test;

import algorithms.mazeGenerators.*;

public class RunMazeGenerator
{
    public static void main(String[] args)
    {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
<<<<<<< HEAD
    // prints the time it takes the algorithm to run
     System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
    // generate another maze
        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);
    // prints the maze
        maze.print();
    // get the maze entrance
        Position startPosition = maze.getStartPosition();
    // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
    // prints the maze exit position
=======
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}