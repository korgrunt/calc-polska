package src.network;

import src.CalcEngine;
import src.dtos.StackRPL;
import src.enums.ModeEnum;
import src.io.StreamManager;
import src.utils.InputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        StackRPL stackRPL = calcEngine.getMode().equals(ModeEnum.REMOTE_SHARED_STACK) ? calcEngine.getGlobalRplStack() : new StackRPL(30);

        /*
         REMOTE LOOP START
         */
        boolean loop = true;
        while (loop) {

            String inputCurrentLineLocal = "";

            // Read user input
            BufferedReader entree = new BufferedReader(new InputStreamReader(streamManager.getIn()));
            try {
                inputCurrentLineLocal = entree.readLine();
            } catch (IOException e) {
                streamManager.getOutUser().println("Error when read line of inputStream");
                throw new RuntimeException(e);
            }

            // Parse local command and execute
            inputCurrentLineLocal = InputUtils.parseAndExecuteNextCommand(inputCurrentLineLocal, stackRPL, streamManager);
            // Print stack
            streamManager.print(stackRPL.toString());

            // Check if need to exit
            if(inputCurrentLineLocal.equals("exit")) {
                streamManager.endProgramMessage();
                loop = false;
            } else {
                streamManager.getOutUser().println("> Enter a value or an operato.");
            }
        }
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
