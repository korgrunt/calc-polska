package src;

import src.enums.CommandEnum;

import java.lang.*;
import java.io.*;
import java.util.Arrays;


public class InputUtils {


    final static String[] AVAILABLE_OPERATOR = {"+", "-", "*", "/"};
    final static String[] AVAILABLE_VALUE = {"+", "-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static boolean isOperator(String input){
        boolean isOperator = false;
        final boolean IS_ONE_CHAR = input.length() == 1;

        if(IS_ONE_CHAR){
            for(String operator : AVAILABLE_OPERATOR){
                if(input.equals(operator)){
                    isOperator = true;
                    break;
                }
            }
        }

        return isOperator;
    }

    public static boolean isValue(String input){

        return true;

    }

    public static boolean isQuitInstruction(String input){
        return input.equalsIgnoreCase("quit") ||
         input.equalsIgnoreCase("exit");
    }

    public static boolean isCommandInput(String value){
        try {
            CommandEnum.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValueInput(String value){
        try
        {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public static String askUserInput() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in) );

        String name = reader.readLine();

        return name;

    };

}   
