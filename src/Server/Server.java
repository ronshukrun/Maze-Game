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
    /**
     * constructor
     * @param port The port from which the server will wait for clients
     * @param listeningIntervalMS the time the server will wait until it checks for new Client
     * @param strategy the strategy of the client
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations configurations = Configurations.getInstance();
        Object[] config = configurations.loadProperties();
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool((int) config[0]);
    }
    /**
     * run the Server
     */
    public void start() {
        new Thread(this::startInner).start();
    }
    /**
     * run the Server
     * open a new ServerSocket, wait for Clients to connect to it, and apply it's strategies on them
     */
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
    /**
     * apply the ServerStrategy on the connected Client
     * @param clientSocket the Socket of the connected Client
     */
    private void handleClient(Socket clientSocket) {
        try (clientSocket) {
            this.strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Stop the Server
     */
    public void stop() {
        this.stop = true;
    }
}