package src;

import src.constants.ParsingConstants;
import src.dtos.StackRPL;
import src.dtos.Vector;
import src.enums.LogModeEnum;
import src.enums.UserModeEnum;
import src.io.StreamManager;
import src.network.NetworkUtils;

import java.io.*;
import java.util.regex.Matcher;

import static src.constants.MessageConstants.*;

public class CalcEngine {

    private final StackRPL globalRplStack = new StackRPL(50, System.out);

    private UserModeEnum userMode;
    private LogModeEnum logMode;

    private InputStream inUserWhileLoop;
    private LogModeEnum previousLogModeEnum;// Todo allow switch mode on fly

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
        if (LogModeEnum.REPLAY.equals(logMode) && !(inUserWhileLoop instanceof DataInputStream)) {

            try {
                this.inUserWhileLoop = new DataInputStream(new FileInputStream(streamManager.getLogFileName()));
            } catch (FileNotFoundException e) {
                streamManager.getOutUser().println(ERROR_FILE_OPEN);
                throw new RuntimeException(e);
            }
            inputHasChanged = true;

        } else if (!LogModeEnum.REPLAY.equals(logMode)) {
            inUserWhileLoop = streamManager.getInUser();
            inputHasChanged = true;
        }
        return inputHasChanged;
    }

    public void loopCalc(StreamManager streamManager, StackRPL stackRPL) {
        boolean loop = true;
        // this allow switching input while program run without restart it
        this.inUserWhileLoop = streamManager.getInUser();
        this.previousLogModeEnum = this.logMode;

        BufferedReader entree = new BufferedReader(new InputStreamReader(inUserWhileLoop));

        while (loop) {

            String inputCurrentLineLocal = "";


            inputCurrentLineLocal = NetworkUtils.isHttpEntry(streamManager.getInUser());


            try {
                if(inputCurrentLineLocal == null){
                    inputCurrentLineLocal = entree.readLine();
                }

            } catch (IOException e) {
                streamManager.getOutUser().println(ERROR_READ_INPUTSTREAM);
                inputCurrentLineLocal = "exit";
            }
            // LOG if log mode is active (don't log exit for allow user to use programme after ll replay
            if (LogModeEnum.LOG.equals(logMode) && !inputCurrentLineLocal.equals("exit"))
                streamManager.logCommand(inputCurrentLineLocal);
            // Parse local command and execute
            if (inputCurrentLineLocal != null) {
                inputCurrentLineLocal = parseAndExecuteNextCommand(inputCurrentLineLocal, stackRPL, streamManager.getOutUser());
                // Print stack
                streamManager.print(stackRPL.toString());
                // Check if should exit
                if (inputCurrentLineLocal.equals("exit")) {
                    streamManager.endProgramMessage();
                    loop = false;
                } else {
                    streamManager.getOutUser().println(ASK_ENTER_VAL_OR_CMD);
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
        Matcher matcherImplcitCommand;

        System.out.println("in parsing function");

        matcher = ParsingConstants.PUSH_V3.matcher(lineRead);
        matcherImplcitCommand = ParsingConstants.IMPLICIT_PUSH_V3.matcher(lineRead);
        if (matcher.find() || matcherImplcitCommand.find()) {

            stack.push(new Vector(
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(1) : matcherImplcitCommand.group(1)),
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(2) : matcherImplcitCommand.group(2)),
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(3) : matcherImplcitCommand.group(3))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.PUSH_V2.matcher(lineRead);
        matcherImplcitCommand = ParsingConstants.IMPLICIT_PUSH_V2.matcher(lineRead);
        if (!commandIsParsed && (matcher.find() || matcherImplcitCommand.find())) {

            stack.push(new Vector(
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(1) : matcherImplcitCommand.group(1)),
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(2) : matcherImplcitCommand.group(2))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.PUSH_V1.matcher(lineRead);
        matcherImplcitCommand = ParsingConstants.IMPLICIT_PUSH_V1.matcher(lineRead);
        if (!commandIsParsed && (matcher.find() || matcherImplcitCommand.find())) {

            stack.push(new Vector(
                    Integer.parseInt(matcher.hasMatch() ? matcher.group(1) : matcherImplcitCommand.group(1))            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.ADD.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            stack.add();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.SUB.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            stack.substract();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.DIV.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            stack.divide();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.MULTIPLY.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            stack.multiply();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.POP.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            stack.pop();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = ParsingConstants.EXIT.matcher(lineRead);
        if (!commandIsParsed && matcher.find()) {
            nextCommandsLineReader = "exit";
            commandIsParsed = true;
        }

        if (!commandIsParsed && nextCommandsLineReader.length() > 1) {
            outputUser.println(UNKNOW_COMMAND);
            nextCommandsLineReader = lineRead.substring(1);
        }

        System.out.println("end parsing function");
        System.out.println("Command has been parsed" + commandIsParsed);

        return nextCommandsLineReader;
    }
}
