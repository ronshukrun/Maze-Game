package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;
    /**
     * constructor
     * @param serverIP the IP Address of the Server
     * @param serverPort The port the server is waiting for a client from
     * @param strategy the strategy of the client
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        if (serverIP == null || clientStrategy == null || serverPort <= 0) {
            throw new IllegalArgumentException("Invalid parameters for Client constructor.");
        }
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }
    /**
     * A function used to connect with the server and apply the Client strategy
     * Catches the exception in case the connection fails
     */
    public void communicateWithServer() {
        try (Socket serverSocket = new Socket(serverIP, serverPort)) {
            System.out.println(String.format("Client is connected to server (IP: %s, Port: %s)",serverIP,serverPort));
            clientStrategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
