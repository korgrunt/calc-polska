package src.network;

import src.CalcEngine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiConnectionServer extends Thread{
    private final int PORT = 8888;
    private ServerSocket serverSocket;
    private boolean serverOpen = false;
    private final CalcEngine calcEngine;

    public MultiConnectionServer(CalcEngine calcEngine) {
        this.calcEngine = calcEngine;;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Error server create");
            System.out.println(e);
        }
    }

    public void run() {
        this.startServer();
    }

    public void startServer() {
        if(!serverOpen) {
            serverOpen = true;
            while (serverOpen) {
                try {
                    // listen
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New connection from: " + clientSocket.getInetAddress());

                    // create client connection handlind socket connection
                    ClientHandlerThreaded client = new ClientHandlerThreaded(clientSocket, calcEngine);
                    client.start();
                } catch (IOException e) {
                    this.stopServer();
                }
            }
        }
    }

    public void stopServer() {
        if(serverOpen && this.isAlive()) {
            serverOpen = false;
            try {
                serverSocket.close();
                System.out.println("Server is closed");
            } catch (IOException e) {
                System.out.println("Error when close socket");
            }
        }
    }


}