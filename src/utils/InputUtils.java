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





}   
