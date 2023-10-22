package src.io;

import src.enums.ModeEnum;

import java.io.InputStream;
import java.io.PrintStream;

public class StreamManager {

    public PrintStream outUser;
    public PrintStream outLog;
    public InputStream in;
    public ModeEnum mode;

    public StreamManager(PrintStream output, InputStream input) {

        this.outUser = output;
        this.in = input;
        this.displayStartMessage();
        if(output == System.out){
            this.outUser.println("\nAs local user, you are admin of the stack rpl and you see each connection entrance");
        }
    }

    public void endProgramMessage() {
        outUser.println("--- Bye bye, see you next time.");
    }

    public void print(String message){
        outUser.println(message);
    }
    public void displayStartMessage() {
        outUser.println("---- Welcome on Polska Calculator, currently in mode " + mode);
        outUser.println("--- You can use it with vector2 of integer composate of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 and with command [add, push, mul, div, sub, exit]");
        outUser.println("-- You know how polska calculator work");
        outUser.println("- For quite the program, simply enter 'exit' and press Enter");
    }

    public PrintStream getOutUser() {
        return outUser;
    }

    public InputStream getIn() {
        return in;
    }
}
