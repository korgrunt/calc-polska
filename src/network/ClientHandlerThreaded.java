package src.network;

import src.CalcEngine;
import src.dtos.StackRPL;
import src.enums.UserModeEnum;
import src.io.StreamManager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static src.constants.MessageConstants.STREAM_MANAGER_ERROR;

class ClientHandlerThreaded extends Thread {
    private final Socket clientSocket;
    private final CalcEngine calcEngine;
    private StreamManager streamManager;

    public ClientHandlerThreaded(Socket clientSocket, CalcEngine calcEngine) {
        this.clientSocket = clientSocket;
        this.calcEngine = calcEngine;
        try {
            this.streamManager = new StreamManager(new PrintStream(clientSocket.getOutputStream()), clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(String.format(STREAM_MANAGER_ERROR, e.getMessage()));
        }
    }

    public void run() {

        this.handleConnection();

    }

    public void handleConnection() {

        // shared or separate stack setted here
        StackRPL stackRPL = calcEngine.getUserMode().toString().contains("REMOTE_SHARED") ? calcEngine.getGlobalRplStack() : new StackRPL(30, streamManager.getOutUser());

        /*
         REMOTE LOOP START
         */
        calcEngine.loopCalc(streamManager, stackRPL);
        /*
         REMOTE LOOP END
         */

        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
