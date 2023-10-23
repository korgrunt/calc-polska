package src.constants;

import java.util.regex.Pattern;

public class ParsingConstants {

    public final static Pattern PUSH_V2 = Pattern.compile("^push (\\d+) (\\d+)");
    public final static Pattern ADD_V2 = Pattern.compile("^add(\\s|$)");
    public final static Pattern SUB_V2 = Pattern.compile("^sub(\\s|$)");
    public final static Pattern DIV_V2 = Pattern.compile("^div(\\s|$)");
    public final static Pattern MULTIPLY_V2 = Pattern.compile("^mul(\\s|$)");
    public final static Pattern IMPLICIT_PUSH_V2 = Pattern.compile("^(\\d+) (\\d+)");

    public final static Pattern POP = Pattern.compile("^pop(\\s|$)");
    public final static Pattern EXIT = Pattern.compile("^exit(\\s|$)");


    public final static Pattern IMPLICIT_PUSH_V1 = Pattern.compile("^(\\d+)");
}
