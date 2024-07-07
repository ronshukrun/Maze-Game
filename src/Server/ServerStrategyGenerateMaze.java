package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.Properties;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             MyCompressorOutputStream compressor = new MyCompressorOutputStream(outputStream)) {

            int[] mazeSizes = (int[]) fromClient.readObject();
            AMazeGenerator mazeGenerator = findMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSizes[0], mazeSizes[1]);

            compressor.write(maze.toByteArray());
            toClient.writeObject(outputStream.toByteArray());
            toClient.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AMazeGenerator findMazeGenerator() {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
            return switch (mazeGeneratingAlgorithm) {
                case "EmptyMazeGenerator" -> new EmptyMazeGenerator();
                case "SimpleMazeGenerator" -> new SimpleMazeGenerator();
                default -> new MyMazeGenerator();
            };
        } catch (IOException e) {
            e.printStackTrace();
            return new MyMazeGenerator();
        }
    }
}
