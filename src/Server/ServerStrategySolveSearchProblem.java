package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Properties;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private static int solutionNumber = 1;
    private final String tempDirectoryPath;
    private final Hashtable<String, String> hashtableSolutions;
    private final String hashPath;

    /**
     * constructor
     * initialize two fields:
     * 1. counter = Atomic integer that provide us the number of the mazeFiles created in the past
     * 2. Hashmap = the hashMap <Maze.hashStr(), solFileName>
     * when we start the server, it's check for mazes/solutions he created in the past, and add them to the hashMap
     */
    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");
        this.hashPath = tempDirectoryPath + "hashtableSolutions.ser";
        this.hashtableSolutions = loadHashTable();
    }

    private Hashtable<String, String> loadHashTable() {
        File hashFile = new File(this.hashPath);
        if (hashFile.exists()) {
            try (FileInputStream fileIn = new FileInputStream(this.hashPath);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                return (Hashtable<String, String>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new Hashtable<>();
    }
    /**
     * A function that receives a maze from the client and return to him the solution of the maze
     * @param inFromClient the Server's Input stream
     * @param outToClient the Server's Output stream
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try (ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
             ObjectOutputStream toClient = new ObjectOutputStream(outToClient)) {

            Maze maze = (Maze) fromClient.readObject();
            byte[] compressedMaze = mazeToCompress(maze);
            Solution solution = getSolutionFromHash(compressedMaze);

            if (solution == null) {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ASearchingAlgorithm searchingAlgorithm = findSearchAlgorithm();
                solution = searchingAlgorithm.solve(searchableMaze);
                saveSolution(compressedMaze, solution);
            }

            toClient.writeObject(solution);
            toClient.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ASearchingAlgorithm findSearchAlgorithm() {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm");
            return switch (mazeSearchingAlgorithm) {
                case "BestFirstSearch" -> new BestFirstSearch();
                case "BreadthFirstSearch" -> new BreadthFirstSearch();
                default -> new DepthFirstSearch();
            };
        } catch (IOException e) {
            e.printStackTrace();
            return new BestFirstSearch();
        }
    }

    private Solution getSolutionFromHash(byte[] compressedMaze) throws IOException, ClassNotFoundException {
        String hashKey = ourToString(compressedMaze);
        if (hashtableSolutions.containsKey(hashKey)) {
            String solutionPath = hashtableSolutions.get(hashKey);
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(solutionPath))) {
                return (Solution) in.readObject();
            }
        }
        return null;
    }

    private void saveSolution(byte[] compressedMaze, Solution solution) throws IOException {
        String fileName = String.valueOf(getSolutionNumber());
        String solutionPath = tempDirectoryPath + fileName + "_" + System.currentTimeMillis() + ".Solution";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(solutionPath))) {
            out.writeObject(solution);
        }

        hashtableSolutions.put(ourToString(compressedMaze), solutionPath);
        try (ObjectOutputStream outHash = new ObjectOutputStream(new FileOutputStream(this.hashPath))) {
            outHash.writeObject(this.hashtableSolutions);
        }
        solutionNumber++;
    }

    public static int getSolutionNumber() {
        return solutionNumber;
    }

    private String ourToString(byte[] compressedMaze) {
        return new String(compressedMaze, StandardCharsets.UTF_8);
    }

    private byte[] mazeToCompress(Maze maze) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (MyCompressorOutputStream compressor = new MyCompressorOutputStream(byteArrayOutputStream)) {
            compressor.write(maze.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
