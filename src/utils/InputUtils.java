package src.utils;

import src.dtos.StackRPL;
import src.io.StreamManager;
import src.dtos.Vector2;

import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputUtils {


    private final static Pattern PUSH_V2 = Pattern.compile("^push (\\d+) (\\d+)");
    private final static Pattern ADD_V2 = Pattern.compile("^add(\\s|$)");
    private final static Pattern SUB_V2 = Pattern.compile("^sub(\\s|$)");
    private final static Pattern DIV_V2 = Pattern.compile("^div(\\s|$)");
    private final static Pattern MULTIPLY_V2 = Pattern.compile("^mul(\\s|$)");
    private final static Pattern IMPLICIT_PUSH_V2 = Pattern.compile("^(\\d+) (\\d+)");

    private final static Pattern POP = Pattern.compile("^pop(\\s|$)");
    private final static Pattern EXIT = Pattern.compile("^exit(\\s|$)");


    private final static Pattern IMPLICIT_PUSH_V1 = Pattern.compile("^(\\d+)");


    public static String parseAndExecuteNextCommand(String lineReaded, StackRPL stack, StreamManager streamManager) {


        String nextCommandsLineReader = "";
        boolean commandIsParsed = false;
        Matcher matcher;

        matcher = InputUtils.PUSH_V2.matcher(lineReaded);
        if(matcher.find()) {
            stack.push(new Vector2(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.ADD_V2.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            stack.add();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.SUB_V2.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            stack.substract();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.DIV_V2.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            stack.divide();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.MULTIPLY_V2.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            stack.multiply();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.IMPLICIT_PUSH_V2.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {

            stack.push(new Vector2(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            ));
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.POP.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            stack.pop();
            nextCommandsLineReader = matcher.replaceAll("");
            commandIsParsed = true;
        }

        matcher = InputUtils.EXIT.matcher(lineReaded);
        if(!commandIsParsed && matcher.find()) {
            nextCommandsLineReader = "exit";
            commandIsParsed = true;
        }

        if(!commandIsParsed && nextCommandsLineReader.length() > 1){
            streamManager.outUser.println("unknow command, impossible to parse it, please enter a valid command and value");
            nextCommandsLineReader = lineReaded.substring(1);
        }

        return nextCommandsLineReader;

    }


}   
