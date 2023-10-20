package src;

import src.enums.ModeEnum;

import java.io.InputStream;
import java.io.PrintStream;

public class StreamManager {

    PrintStream outUser;
    PrintStream outLog;
    InputStream in;


    ModeEnum mode;



    public StreamManager(String[] args) {
        if(args == null || args.length < 1){
            mode = ModeEnum.LOCAL;
        } else {
            mode = ParseArgsUtils.argToEnumMode(args[0]);
        }
        setStreamMode(mode);
    }

    public void setStreamMode(ModeEnum modeEnum){
        if(ModeEnum.LOCAL.equals(modeEnum)){
            outUser = System.out;
            in = System.in;
        } else  if(ModeEnum.REPLAY.equals(modeEnum)){
            outUser = System.out;
            in = System.in;
        } else if(ModeEnum.REMOTE_SHARED_STACK.equals(modeEnum)){
            outUser = System.out;
            in = System.in;
        } else if(ModeEnum.REMOTE_SHARED_NOT_STACK.equals(modeEnum)){
            outUser = System.out;
            in = System.in;
        }

    }
    public void setStreamUser(PrintStream out, InputStream in){
        this.outUser = out;
        this.in = in;
    }
    public void setStreamLog(PrintStream out, InputStream in){
        this.outLog = out;
        this.in = in;
    }
    public void endProgramMessage() {
        outUser.println("--- Bye bye, see you next time.");
    }

    public void print(String message){
        outUser.println(message);
    }
    public void startProgramMessage() {
        outUser.println("---- Welcome on Polska Calculator, currently in mode " + mode);
        outUser.println("--- You can use 0,1,2,3,4,5,6,7,8,9,+,-,*,/");
        outUser.println("-- You know how polska calculator work");
        outUser.println("- For quite the program, simply enter 'quit' and press Enter");
    }
    public PrintStream getOutUser() {
        return outUser;
    }

    public void setOutUser(PrintStream outUser) {
        this.outUser = outUser;
    }

    public PrintStream getOutLog() {
        return outLog;
    }

    public void setOutLog(PrintStream outLog) {
        this.outLog = outLog;
    }

    public InputStream getInUser() {
        return in;
    }

    public void setInUser(InputStream in) {
        this.in = in;
    }

    public ModeEnum getMode() {
        return mode;
    }

    public void setMode(ModeEnum mode) {
        this.mode = mode;
    }
}
