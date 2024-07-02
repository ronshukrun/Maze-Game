package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Properties;
//
//public class Configurations {
//
//    public static int ThreadPoolSize() {
//        try {
//            InputStream input = new FileInputStream("resources/config.properties");
//            Properties prop = new Properties();
//            // load a properties file
//            prop.load(input);
//            // get the property value and print it out
//            return Integer.parseInt(prop.getProperty("threadPoolSize"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return -1;
//        }
//
//    }
//
//    public static IMazeGenerator mazeGeneratingAlgorithm() {
//        try {
//            InputStream input = new FileInputStream("resources/config.properties");
//            Properties prop = new Properties();
//            // load a properties file
//            prop.load(input);
//            // get the property value and print it out
//            String mazeGen = prop.getProperty("mazeGeneratingAlgorithm");
//            if (mazeGen.equals("MyMazeGenerator"))
//                return new MyMazeGenerator();
//            if (mazeGen.equals("SimpleMazeGenerator"))
//                return new SimpleMazeGenerator();
//            if (mazeGen.equals("EmptyMazeGenerator"))
//                return new EmptyMazeGenerator();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }
//        return null;
//    }
//
//    public static ISearchingAlgorithm mazeSearchingAlgorithm() {
//        try {
//            InputStream input = new FileInputStream("resources/config.properties");
//            Properties prop = new Properties();
//            // load a properties file
//            prop.load(input);
//            // get the property value and print it out
//            String mazeGen = prop.getProperty("mazeSearchingAlgorithm");
//            if (mazeGen.equals("BestFirstSearch"))
//                return new BestFirstSearch();
//            if (mazeGen.equals("BreadthFirstSearch"))
//                return new BreadthFirstSearch();
//            if (mazeGen.equals("DepthFirstSearch"))
//                return new DepthFirstSearch();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }
//        return null;
//    }
//
//
//}
//////////

public class Configurations {

    private static Configurations instance = null;
    private Properties prop;

    private Configurations() {
        prop = new Properties();
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public void setProperty(String key, String value) {
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {
            prop.setProperty(key, value);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadPoolSize() {
        return Integer.parseInt(getProperty("threadPoolSize"));
    }

    public IMazeGenerator getMazeGenerator() {
        String gen = getProperty("mazeGeneratingAlgorithm");
        switch (gen) {
            case "MyMazeGenerator":
                return new MyMazeGenerator();
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            default:
                return null;
        }
    }
    public ISearchingAlgorithm getSearchAlgorithm() {
        String alg = getProperty("mazeSearchingAlgorithm");
        switch (alg) {
            case "BestFirstSearch":
                return new BestFirstSearch();
            case "BreadthFirstSearch":
                return new BreadthFirstSearch();
            case "DepthFirstSearch":
                return new DepthFirstSearch();
            default:
                return null;
        }
    }
}
