package src.io;

import java.io.*;

public class StreamManager {




    private final String logFileName = "./CALG_LOG.log";
    public PrintStream outUser;
    public BufferedWriter outLog;

    public InputStream inUser;

    public String getLogFileName() {
        return logFileName;
    }

/*

    public InputStream getLogFileAsIn(){
        if(this.inLogFile == null){
            try {
                new DataInputStream(new FileInputStream(new File("CALG_LOG.log")));
                this.inLogFile = new FileReader(logFileName);
            } catch (FileNotFoundException e) {
                this.outUser.println("Can't read log file named " + logFileName);
                this.outUser.println("Got error when read file " + e.getMessage());

                throw new RuntimeException(e);
            }
        }
        return this.inLogFile;
    }
 */

    public StreamManager(PrintStream output, InputStream input) {

        this.outUser = output;
        this.inUser = input;
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
        outUser.println("---- Welcome on Polska Calculator");
        outUser.println("--- You can use it with vector2 of integer composate of 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 and with command [add, push, mul, div, sub, exit]");
        outUser.println("-- You know how polska calculator work");
        outUser.println("- For quite the program, simply enter 'exit' and press Enter");
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
                this.outUser.println("Can't open log file named " + logFileName);
                this.outUser.println("Got error when open file " + e.getMessage());
            }
        }

        return outLog;
    }

    public void closeLogFile() {
        if(outLog != null){
            try {
                outLog.close();
            } catch (IOException e) {
                this.outUser.println("Can't close log file named " + logFileName);
                this.outUser.println("Got error when close file " + e.getMessage());
            }
        }
    }

    public void logCommand(String command){
        try {
            this.getOutLog().append(command + "\n");
        } catch (IOException e) {
            this.outUser.println("Can't print to log file named " + logFileName);
            this.outUser.println("Got error when trying print to file " + e.getMessage());
        }
    }
}
