package src.constants;

import java.util.regex.Pattern;

public class ParsingConstants {

    // VECTOR 3
    public final static Pattern PUSH_V3 = Pattern.compile("^push (\\d+) (\\d+) (\\d+)");
    public final static Pattern IMPLICIT_PUSH_V3 = Pattern.compile("^(\\d+) (\\d+) (\\d+)");

    // VECTOR 2
    public final static Pattern PUSH_V2 = Pattern.compile("^push (\\d+) (\\d+)");
    public final static Pattern IMPLICIT_PUSH_V2 = Pattern.compile("^(\\d+) (\\d+)");

    // VECTOR 1
    public final static Pattern PUSH_V1 = Pattern.compile("^push (\\d+)");
    public final static Pattern IMPLICIT_PUSH_V1 = Pattern.compile("^(\\d+)");

    // GENERIC
    public final static Pattern ADD = Pattern.compile("^add(\\s|$)");
    public final static Pattern SUB = Pattern.compile("^sub(\\s|$)");
    public final static Pattern DIV = Pattern.compile("^div(\\s|$)");
    public final static Pattern MULTIPLY = Pattern.compile("^mul(\\s|$)");
    public final static Pattern POP = Pattern.compile("^pop(\\s|$)");
    public final static Pattern EXIT = Pattern.compile("^exit(\\s|$)");
}
