package src.network;

import src.CalcEngine;
import src.constants.MessageConstants.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ArrayList;

import static src.constants.MessageConstants.*;

public class MultiConnectionServer extends Thread{
    private final int PORT = 8888;
    private ServerSocket serverSocket;
    private boolean serverOpen = false;
    private final CalcEngine calcEngine;

    private ArrayList<Socket> activeSocket;

    public MultiConnectionServer(CalcEngine calcEngine) {
        this.calcEngine = calcEngine;
        this.activeSocket = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println(String.format(ERROR_CREATE_SERVER, e.getMessage()));
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
                    System.out.println(String.format(NEW_CONNECTION, clientSocket.getInetAddress()));
                    activeSocket.add(clientSocket);

                    // create client connection handlind socket connection
                    ClientHandlerThreaded client = new ClientHandlerThreaded(clientSocket, calcEngine);
                    if(this.calcEngine.getUserMode().toString().contains("USERS")){
                        client.start();
                    } else {
                        client.handleConnection();
                    }
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
                for(Socket openSocket : activeSocket){
                    openSocket.close();
                }
                serverSocket.close();
                System.out.println(SERVER_CLOSE);
            } catch (IOException e) {
                System.out.println(String.format(ERROR_CLOSE_SOCKET, e.getMessage()));
            }
        }
    }


}