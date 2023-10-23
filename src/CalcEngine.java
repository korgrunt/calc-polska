package src;

import src.dtos.StackRPL;
import src.dtos.Vector2;
import src.constants.ParsingConstants;
import src.enums.LogModeEnum;
import src.enums.UserModeEnum;
import src.io.StreamManager;

import java.io.*;
import java.util.regex.Matcher;

public class CalcEngine {


    private final StackRPL globalRplStack = new StackRPL(50);

    private UserModeEnum userMode;
    private LogModeEnum logMode;

    private InputStream inUserWhileLoop;
    private LogModeEnum previousLogModeEnum;
    public CalcEngine(UserModeEnum initialUserMode, LogModeEnum initialLogMode) {
        this.userMode = initialUserMode;
        this.logMode = initialLogMode;
    }

    /*
    GETTERS AND SETTERS
     */
    public UserModeEnum getUserMode() {
        return userMode;
    }

    public void setUserMode(UserModeEnum mode) {
        this.userMode = mode;
    }

    public StackRPL getGlobalRplStack() {
        return globalRplStack;
    }

    private boolean switchInputSourceIfNeed(StreamManager streamManager) {
        boolean inputHasChanged = false;
        if(LogModeEnum.REPLAY.equals(logMode) && !(inUserWhileLoop instanceof DataInputStream)) {

            try {
                this.inUserWhileLoop = new DataInputStream(new FileInputStream(streamManager.getLogFileName()));
            } catch (FileNotFoundException e) {
                streamManager.getOutUser().println("Can't open file");
                throw new RuntimeException(e);
            }
            inputHasChanged = true;

        } else if (!LogModeEnum.REPLAY.equals(logMode)){
            inUserWhileLoop = streamManager.getInUser();
            inputHasChanged = true;
        }
        return inputHasChanged;
    }

    public void loopCalc(StreamManager streamManager, StackRPL stackRPL){
        boolean loop = true;
        // this allow switching input while program run without restart it
        this.inUserWhileLoop = streamManager.inUser;
        this.previousLogModeEnum = this.logMode;

        BufferedReader entree = new BufferedReader(new InputStreamReader(inUserWhileLoop));;
        while (loop) {

            String inputCurrentLineLocal = "";


            boolean inputHasChanged = this.switchInputSourceIfNeed(streamManager);

            // Read line from input
            if(inputHasChanged) {
                entree = new BufferedReader(new InputStreamReader(inUserWhileLoop));
            }

            try {
                inputCurrentLineLocal = entree.readLine();
            } catch (IOException e) {
                streamManager.getOutUser().println("Error when read line of inputStream");
                throw new RuntimeException(e);
            }
            // LOG if log mode is active (don't log exit for allow user to use programme after ll replay
            if(LogModeEnum.LOG.equals(logMode) && !inputCurrentLineLocal.equals("exit")) streamManager.logCommand(inputCurrentLineLocal);
            // Parse local command and execute
            if(inputCurrentLineLocal != null){
                inputCurrentLineLocal = parseAndExecuteNextCommand(inputCurrentLineLocal, stackRPL, streamManager.getOutUser());
                // Print stack
                streamManager.print(stackRPL.toString());
                // Check if should exit
                if(inputCurrentLineLocal.equals("exit")) {
                    streamManager.endProgramMessage();
                    loop = false;
                } else {
                    streamManager.getOutUser().println("> Enter a value or an operato.");
                }
            } else {
                this.logMode = LogModeEnum.NO_LOG;
            }

        }
    }

    public static String parseAndExecuteNextCommand(String lineRead, StackRPL stack, PrintStream outputUser) {


        String nextCommandsLineReader = "";
        boolean commandIsParsed = false;
        Matcher matcher;

        matcher = ParsingConstants.PUSH_V2.matcher(lineRead);
        if(matcher.find()) {
            stack.push(new Vector2(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.ADD_V2.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            stack.add();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.SUB_V2.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            stack.substract();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.DIV_V2.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            stack.divide();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.MULTIPLY_V2.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            stack.multiply();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.IMPLICIT_PUSH_V2.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {

            stack.push(new Vector2(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.POP.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            stack.pop();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.EXIT.matcher(lineRead);
        if(!commandIsParsed && matcher.find()) {
            nextCommandsLineReader = "exit";
            commandIsParsed = true;
        }

        if(!commandIsParsed && nextCommandsLineReader.length() > 1){
            outputUser.println("unknow command, impossible to parse it, please enter a valid command and value");
            nextCommandsLineReader = lineRead.substring(1);
        }

        return nextCommandsLineReader;

    }
}
