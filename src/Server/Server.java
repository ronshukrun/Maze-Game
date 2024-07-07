package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final int listeningIntervalMS;
    private final IServerStrategy strategy;
    private volatile boolean stop;
    private final ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations configurations = Configurations.getInstance();
        Object[] config = configurations.loadProperties();
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool((int) config[0]);
    }

    public void start() {
        new Thread(this::startInner).start();
    }

    private void startInner() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            serverSocket.setSoTimeout(this.listeningIntervalMS);

            while (!this.stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    this.threadPool.execute(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    // No client connected within the interval, continue waiting
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.threadPool.shutdownNow();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (clientSocket) {
            this.strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }
}