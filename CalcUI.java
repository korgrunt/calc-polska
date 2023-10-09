import java.lang.*;
import java.io.*;

public class CalcUI {

    public static void main(String[] args)
        throws IOException
        {
            PileRPL rplStack = new PileRPL(20); 


            // Welcome message
            System.out.println("---- Welcome on Polska Calculator");
            System.out.println("--- You can use 0,1,2,3,4,5,6,7,8,9,+,-,*,/");
            System.out.println("-- You know how polska calculator work");
            System.out.println("- For quite the program, simply enter 'quit' and press Enter");

            // If user enter "quit", this passing to false and while will stop and finish program
            boolean programShouldStop = false;
            boolean isValidInput;

            while(!programShouldStop){
                // Scan input from console
                String input = InputUtils.askUserInput();
                // Check instruction is quit or not  
                programShouldStop = InputUtils.isQuitInstruction(input);
                if(programShouldStop) break;

                // Use input
                final ObjEmp objectEmp = ObjEmp.parseInput(input) ;
                if(objectEmp != null){                  
                    rplStack.push(objectEmp);
                } else if (InputUtils.isOperator(input)) {

                    switch (input) {
                        case "+":
                            rplStack.add();
                            break;
                        case "-":
                            rplStack.substract();
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
                rplStack.printStack();
                System.out.println("> Enter a value or an operato.");
            }


            // Stop message
            System.out.println("--- Bye bye, see you next time.");
            return;

        }


}   
