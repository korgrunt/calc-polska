package src.dtos;

import java.lang.*;

public class StackRPL {

    private Vector2[] stack;
    private int nbObj;
    private final static int DEFAULT_MAX_SIZE = 42;

    public StackRPL(int maxSize){
        this.stack = new Vector2[maxSize];
        this.nbObj = 0;
    }

    public StackRPL(){
        this(DEFAULT_MAX_SIZE);
    }

    public void add(){

        if(this.nbObj < 2){
            System.out.println("Cannot add, not enough object in stack");
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.add(obj1));
        }
    }

    public void substract(){

        if(this.nbObj < 2){
            System.out.println("Cannot substract, not enough object in stack");
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.substract(obj1));
        }
    }

    public void divide(){

        if(this.nbObj < 2){
            System.out.println("Cannot substract, not enough object in stack");
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.divide(obj1));
        }
    }

    public void multiply(){

        if(this.nbObj < 2){
            System.out.println("Cannot substract, not enough object in stack");
        } else {

            Vector2 obj1 = this.pop();
            Vector2 obj2 = this.pop();
            this.push(obj2.multiply(obj1));
        }
    }

    public Vector2 pop(){

        Vector2 obj;
        if(this.nbObj <= 0){
            System.out.println("CANNOT POP, nbOBJ is" + this.nbObj);
            obj = null;
        } else {
            obj = this.stack[this.nbObj -1];
            this.nbObj--;
        }
        return obj;
    }


    public void push(Vector2 objEmp){

        if(this.nbObj == this.stack.length){
            System.out.println("Stack is full, cannot push.");
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
