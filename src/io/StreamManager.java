package src.io;

import java.io.*;

import static src.constants.MessageConstants.*;

public class StreamManager {




    private final String logFileName = "./CALG_LOG.log";
    public PrintStream outUser;
    public BufferedWriter outLog;

    public InputStream inUser;

    public String getLogFileName() {
        return logFileName;
    }

    public StreamManager(PrintStream output, InputStream input) {

        this.outUser = output;
        this.inUser = input;
        this.displayStartMessage();
        if(output == System.out){
            this.outUser.println(ADMIN_MESSAGE_CONNECTION_VISIBILITY);
        }
    }

    public void endProgramMessage() {
        outUser.println(GOODBY_MESSAGE);
    }

    public void print(String message){
        outUser.println(message);
    }
    public void displayStartMessage() {
        outUser.println(WELCOME_MESSAGE);
    }

    public PrintStream getOutUser() {
        return outUser;
    }

    public InputStream getInUser() {
        return inUser;
    }
    public void setInUser(InputStream input) {
        this.inUser = input;
    }

    public BufferedWriter getOutLog() {
        if(outLog == null){
            try {
                outLog = new BufferedWriter(new FileWriter(logFileName));
            } catch (IOException e) {
                this.outUser.println(String.format(CANT_OPEN_FILE, logFileName, e.getMessage()));
            }
        }

        return outLog;
    }

    public void closeLogFile() {
        if(outLog != null){
            try {
                outLog.close();
            } catch (IOException e) {
                this.outUser.println(String.format(CANT_CLOSE_FILE, logFileName, e.getMessage()));
            }
        }
    }

    public void logCommand(String command){
        try {
            this.getOutLog().append(command + "\n");
        } catch (IOException e) {
            this.outUser.println(String.format(CANT_PRINT_IN_LOGFILE, logFileName, e.getMessage()));
        }
    }
}
