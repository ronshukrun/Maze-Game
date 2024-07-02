package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {
        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient)) {

            Maze maze = (Maze) fromClient.readObject();
            Solution solution = findSolutionFromResources(maze);

            if (solution == null) {
                ISearchingAlgorithm searchAlgorithm = Configurations.getInstance().getSearchAlgorithm();
                solution = searchAlgorithm.solve(new SearchableMaze(maze));
                writeSolutionToResources(maze, solution);
            }

            toClient.writeObject(solution);
        }
    }

    private synchronized Solution findSolutionFromResources(Maze maze) throws IOException, ClassNotFoundException {
        byte[] mazeBytes = maze.toByteArray();
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        File dir = new File(tempDirectoryPath + "/MazeAndSolution");

        if (!dir.exists()) return null;

        for (File mazeDir : dir.listFiles()) {
            File mazeFile = new File(mazeDir, "Maze");
            if (Arrays.equals(Files.readAllBytes(mazeFile.toPath()), mazeBytes)) {
                try (ObjectInputStream fromSolutionFile = new ObjectInputStream(new FileInputStream(new File(mazeDir, "Solution")))) {
                    return (Solution) fromSolutionFile.readObject();
                }
            }
        }
        return null;
    }

    private synchronized void writeSolutionToResources(Maze maze, Solution solution) throws IOException {
        byte[] mazeBytes = maze.toByteArray();
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        File dir = new File(tempDirectoryPath + "/MazeAndSolution");
        if (!dir.exists()) dir.mkdir();

        File mazeDir = new File(dir, "Maze" + dir.listFiles().length);
        mazeDir.mkdir();

        try (FileOutputStream mazeOutputStream = new FileOutputStream(new File(mazeDir, "Maze"));
             ObjectOutputStream solutionOutputStream = new ObjectOutputStream(new FileOutputStream(new File(mazeDir, "Solution")))) {

            mazeOutputStream.write(mazeBytes);
            solutionOutputStream.writeObject(solution);
        }
    }
}
