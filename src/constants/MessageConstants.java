package src.constants;

public class MessageConstants {

    public final static String ERROR_READ_INPUTSTREAM = "Error when read line of inputStream";
    public final static String ERROR_FILE_OPEN = "Can't open file";
    public final static String ASK_ENTER_VAL_OR_CMD = "Please, enter a value or a command.";
    public final static String UNKNOW_COMMAND = "Unknow command, impossible to parse it, please enter a valid command and value";
    public final static String ERROR_CREATE_SERVER = "Error when server creattion, got %s";
    public final static String NEW_CONNECTION = "New connection from: %s";
    public final static String SERVER_CLOSE = "Server is closed";
    public final static String ERROR_CLOSE_SOCKET = "Error when close socket, got error: %s";
    public final static String STREAM_MANAGER_ERROR =  "Unable to set stream manager for this user, got error: %s";

    public final static String WELCOME_MESSAGE = "---- Welcome on Polska Calculator" +
        "\n--- You can use it with vector2 of integer composate of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 and with command [add, push, mul, div, sub, exit]" +
        "\n-- You know how polska calculator work" +
        "\n- For quite the program, simply enter 'exit' and press Enter";

    public final static String ADMIN_MESSAGE_CONNECTION_VISIBILITY = "\nAs local user, you are admin of the stack rpl and you see each connection entrance";
    public final static String GOODBY_MESSAGE = "--- Bye bye, see you next time.";

    public final static String CANT_OPEN_FILE = "Can't open log file named [%s]. Got error: %s";
    public final static String CANT_CLOSE_FILE = "Can't open log file named [%s]. Got error: %s";
    public final static String CANT_PRINT_IN_LOGFILE = "Can't print to log file named [%s]. Got error when trying print to file: %s";
    public final static String INVALID_USER_MODE = "\n---- Invalid user mode provided, get (%s). Please, set argument for mode as valid. exemple: --user=(%s).";
    public final static String INVALID_LOG_MODE = "\n---- Invalid log mode provided, get (%s). Please, set argument for logMode as valid. exemple: --log=(%s).";
    public final static String CANT_ADD = "Cannot add, not enough object in stack";
    public final static String CANT_SUB = "Cannot substract, not enough object in stack";
    public final static String CANT_DIV = "Cannot divide, not enough object in stack";
    public final static String CANT_MUL = "Cannot multiply, not enough object in stack";
    public final static String CANT_POP = "CANNOT POP, nbOBJ is: %s";
    public final static String CANT_PUSH = "Stack is full, cannot push.";

}
