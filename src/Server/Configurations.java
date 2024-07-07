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

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

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
