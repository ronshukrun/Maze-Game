package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations instance = null;
    private Properties prop;

    /**
     * constructor
     * enter default values for the first time we boot the server
     * The file will contain settings for:
     * threadPoolSize, mazeGeneratingAlgorithm, mazeSearchingAlgorithm, CompressorType
     */
    private Configurations() {
        prop = new Properties();
        loadDefaults();
    }

    private void loadDefaults() {
        File configFile = new File("resources/config.properties");
        if (configFile.length() == 0) {
            try (OutputStream output = new FileOutputStream(configFile)) {
                prop.setProperty("threadPoolSize", "10");
                prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
                prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
                prop.setProperty("CompressorType", "MyCompressorOutputStream");
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            try (InputStream input = new FileInputStream(configFile)) {
                prop.load(input);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    public void addProperty(String key, String value) {
        prop.setProperty(key, value);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
    /**
     * check if there is no instance created for 'Configurations'
     * no instance ---> create and return
     * yes instance --> return the instance
     * (singleton design pattern)
     * @return the Only Instance(Configurations)
     */
    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }
    /**
     * write the properties obtained as parameters to the configuration file
     * override the existing properties
     * @param threadPoolSize Number of threads to be opened
     * @param mazeGeneratingAlgorithm The algorithm for creating a maze
     * @param mazeSearchingAlgorithm The algorithm for solving a maze
     * @param compressorType the Compression algorithm
     */
    public void writeProperties(String threadPoolSize, String mazeGeneratingAlgorithm, String mazeSearchingAlgorithm, String compressorType) {
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {
            prop.setProperty("threadPoolSize", threadPoolSize);
            prop.setProperty("mazeGeneratingAlgorithm", mazeGeneratingAlgorithm);
            prop.setProperty("mazeSearchingAlgorithm", mazeSearchingAlgorithm);
            prop.setProperty("CompressorType", compressorType);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    /**
     * load the properties from the configuration file
     * @return array of the properties from the configuration file(Object[])
     */
    public Object[] loadProperties() {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            prop.load(input);
            String[] properties = new String[4];
            properties[0] = prop.getProperty("threadPoolSize");
            properties[1] = prop.getProperty("mazeGeneratingAlgorithm");
            properties[2] = prop.getProperty("mazeSearchingAlgorithm");
            properties[3] = prop.getProperty("CompressorType");

            Object[] settings = new Object[4];
            settings[0] = Integer.parseInt(properties[0]);

            settings[1] = switch (properties[1]) {
                case "EmptyMazeGenerator" -> new EmptyMazeGenerator();
                case "SimpleMazeGenerator" -> new SimpleMazeGenerator();
                default -> new MyMazeGenerator();
            };

            settings[2] = switch (properties[2]) {
                case "BestFirstSearch" -> new BestFirstSearch();
                case "BreadthFirstSearch" -> new BreadthFirstSearch();
                default -> new DepthFirstSearch();
            };

            settings[3] = properties[3];
            return settings;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
