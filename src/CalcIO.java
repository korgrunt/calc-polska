package src;

import src.io.StreamManager;
import src.utils.InputUtils;
import src.network.MultiConnectionServer;
import java.io.*;

public class CalcIO {


    StreamManager localStreamManager = new StreamManager(System.out, System.in);

    MultiConnectionServer server;

    public CalcIO(CalcEngine calcEngine) {

        this.server = new MultiConnectionServer(calcEngine);

        if(calcEngine.getMode().toString().contains("REMOTE")){
            server.start();
        } else { // if mode is NOT remote close server if exist
            server.stopServer();
        }

        boolean loop = true;

        // LOCAL LOOP START
        while (loop) {

            String inputCurrentLineLocal = "";

            // Read line from local context
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStreamManager.getIn()));
            try {
                inputCurrentLineLocal = reader.readLine();
            } catch (IOException e) {
                localStreamManager.getOutUser().println("Error when read line of inputStream");
                throw new RuntimeException(e);
            }

            // Parse local command and execute
            inputCurrentLineLocal = InputUtils.parseAndExecuteNextCommand(inputCurrentLineLocal, calcEngine.getGlobalRplStack(), localStreamManager);

            localStreamManager.print(calcEngine.getGlobalRplStack().toString());

            if(inputCurrentLineLocal == "exit") {
                localStreamManager.endProgramMessage();
                if(server.isAlive()) server.stopServer();
                loop = false;
            } else {
                localStreamManager.getOutUser().println("> Enter a value or an operato.");
            }
        }
    }
}
