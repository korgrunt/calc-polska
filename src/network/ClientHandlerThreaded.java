package src.network;

import src.CalcEngine;
import src.dtos.StackRPL;
import src.enums.UserModeEnum;
import src.io.StreamManager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class ClientHandlerThreaded extends Thread {
    private Socket clientSocket;
    private CalcEngine calcEngine;
    private StreamManager streamManager;

    public ClientHandlerThreaded(Socket clientSocket, CalcEngine calcEngine) {
        this.clientSocket = clientSocket;
        this.calcEngine = calcEngine;
        try {
            this.streamManager = new StreamManager(new PrintStream(clientSocket.getOutputStream()), clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("Unable to set stream manager for this user");
        }
    }

    public void run() {

        // shared or separate stack setted here
        StackRPL stackRPL = calcEngine.getUserMode().equals(UserModeEnum.REMOTE_SHARED_STACK) ? calcEngine.getGlobalRplStack() : new StackRPL(30);

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
