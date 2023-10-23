package src.dtos;

import java.io.PrintStream;
import java.lang.*;

import static src.constants.MessageConstants.*;

public class StackRPL {



    private PrintStream outputUser;
    private Vector2[] stack;
    private int nbObj;
    private final static int DEFAULT_MAX_SIZE = 42;

    public StackRPL(int maxSize, PrintStream outputUser){
        this.stack = new Vector2[maxSize];
        this.nbObj = 0;
        this.outputUser = outputUser;
    }

    public StackRPL(){
        this(DEFAULT_MAX_SIZE, System.out);
    }


    public void setOutputUser(PrintStream outputUser) {
        this.outputUser = outputUser;
    }

    public void add(){

        if(this.nbObj < 2){
            outputUser.println(CANT_ADD);
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.add(obj1));
        }
    }

    public void substract(){

        if(this.nbObj < 2){
            outputUser.println(CANT_SUB);
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.substract(obj1));
        }
    }

    public void divide(){

        if(this.nbObj < 2){
            outputUser.println(CANT_DIV);
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.divide(obj1));
        }
    }

    public void multiply(){

        if(this.nbObj < 2){
            outputUser.println(CANT_MUL);
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.multiply(obj1));
        }
    }

    public Vector2 pop(){

        Vector2 obj;
        if(this.nbObj <= 0){
            outputUser.println(String.format(CANT_POP, this.nbObj));
            obj = null;
        } else {
            obj = this.stack[this.nbObj -1];
            this.nbObj--;
        }
        return obj;
    }


    public void push(Vector2 objEmp){

        if(this.nbObj == this.stack.length){
            outputUser.println(CANT_PUSH);
        }

        this.stack[this.nbObj] = objEmp;
        this.nbObj++;
    }

    public String toString(){
        String str = "";
        str += "\n+----------+";
        str += "\n!          !";
        
        for(int i = 0; i < this.nbObj; i++){
            String strValue = this.stack[i].getA() + " " + this.stack[i].getB();
            String stringPadding = "";
            for(int a = 0; a < 10 - strValue.length(); a++) stringPadding += " ";
            str += ("\n!" + strValue + stringPadding + "!");
        }

        str += ("\n+----------+");
        return str;
    }

}
