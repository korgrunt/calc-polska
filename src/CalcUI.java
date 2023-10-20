package src;

import src.network.TCPServer;

import java.lang.*;
import java.io.*;

public class CalcUI {

    StreamManager streamManager;

    public CalcUI(String[] args)
            throws IOException {

        PileRPL globalRplStack = new PileRPL(20);

        // INIT STREAM
        streamManager = new StreamManager(args);
        streamManager.startProgramMessage();

        //TCPServer server = new TCPServer();

        // LAUNCH LOOP
        // If user enter "quit", this passing to false and while will stop and finish program
        boolean loop = true;
        boolean isValidInput;

        while (loop) {
            // Scan input from console

            System.out.println("step 1");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(streamManager.getInUser()) );

            System.out.println("step 2");
            String input = reader.readLine();

            System.out.println(input);

            String[] inputSplited = input.split(" ");

            // it's a command
            for(int i = 0; i < inputSplited.length; i++){

                if(InputUtils.isCommandInput(inputSplited[i])){

                } else if (InputUtils.isValueInput(inputSplited[i])) {

                }

            }

            // it's a value


            // Check instruction is quit or not
            loop = !InputUtils.isQuitInstruction(input);
            if (!loop) {
                streamManager.endProgramMessage();
                return;
            }

            // Use input
            final ObjEmp objectEmp = ObjEmp.parseInput(input);
            if (objectEmp != null) {
                globalRplStack.push(objectEmp);
            } else if (InputUtils.isOperator(input)) {

                switch (input) {
                    case "+":
                        globalRplStack.add();
                        break;
                    case "-":
                        globalRplStack.substract();
                        break;
                    case "/":
                        System.out.println("Should divide");
                        break;
                    case "*":
                        System.out.println("Should product");
                        break;
                }


            } else {
                System.out.println("-!-!- Please, enter a valid input, should be an operator or a numeric value.");
            }

            // Print stack
            streamManager.print(globalRplStack.toString());
            System.out.println("> Enter a value or an operato.");
        }


        // Stop message
        System.out.println("--- Bye bye, see you next time.");
        return;
    }

    public static void main(String[] args)
            throws IOException {

        new CalcUI(args);

    }


}   
