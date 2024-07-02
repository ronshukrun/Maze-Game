package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {
        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient)) {

            int[] mazeDimensions = (int[]) fromClient.readObject();
            IMazeGenerator mazeGenerator = Configurations.getInstance().getMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeDimensions[0], mazeDimensions[1]);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream);
            compressor.write(maze.toByteArray());
            compressor.close();

            toClient.writeObject(byteArrayOutputStream.toByteArray());
        }
    }
}
