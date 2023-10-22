package src;

import src.dtos.StackRPL;
import src.enums.ModeEnum;
import src.io.StreamManager;
import src.utils.InputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalcEngine {


    private final StackRPL globalRplStack = new StackRPL(50);

    private ModeEnum mode;

    public CalcEngine(ModeEnum initialMode) {
        this.mode = initialMode;
    }

    /*
    GETTERS AND SETTERS
     */
    public ModeEnum getMode() {
        return mode;
    }

    public void setMode(ModeEnum mode) {
        this.mode = mode;
    }

    public StackRPL getGlobalRplStack() {
        return globalRplStack;
    }

    public void loopCalc(StreamManager streamManager, StackRPL stackRPL){
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

            // Check if should exit
            if(inputCurrentLineLocal.equals("exit")) {
                streamManager.endProgramMessage();
                loop = false;
            } else {
                streamManager.getOutUser().println("> Enter a value or an operato.");
            }
        }
    }
}
