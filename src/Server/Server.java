package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getInstance().getThreadPoolSize());
    }

    public void start() {
        new Thread(this::startServer).start();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(new HandleClient(clientSocket, strategy));
                } catch (IOException e) {
                    System.out.println("Connection timed out");
                }
            }
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }

    private static class HandleClient implements Runnable {
        private Socket clientSocket;
        private IServerStrategy strategy;

        public HandleClient(Socket clientSocket, IServerStrategy strategy) {
            this.clientSocket = clientSocket;
            this.strategy = strategy;
        }

        @Override
        public void run() {
            try {
                strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
