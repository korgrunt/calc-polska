package src;

import src.io.StreamManager;
import src.network.MultiConnectionServer;

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

        /*
         LOCAL LOOP START
         */
        calcEngine.loopCalc(localStreamManager, calcEngine.getGlobalRplStack());
        /*
         LOCAL LOOP END
         */

        if(server.isAlive()) server.stopServer();
    }
}
