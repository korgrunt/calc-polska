package src;

import java.lang.*;

public class PileRPL {

    private ObjEmp[] stack;
    private int nbObj;
    private final static int DEFAULT_MAX_SIZE = 42;

    public PileRPL(int maxSize){
        this.stack = new ObjEmp[maxSize];
        this.nbObj = 0;
    }

    public PileRPL(){
        this(DEFAULT_MAX_SIZE);
    }

    public void add(){

        if(this.nbObj < 2){
            System.out.println("Cannot add, not enough object in stack");
        } else {

            ObjEmp obj1 = this.pop();  
            ObjEmp obj2 = this.pop();  
            this.push(obj2.add(obj1));
        }
    }

    public void substract(){

        if(this.nbObj < 2){
            System.out.println("Cannot substract, not enough object in stack");
        } else {

            ObjEmp obj1 = this.pop();  
            ObjEmp obj2 = this.pop();  
            this.push(obj2.substract(obj1));
        }
    }

    public ObjEmp pop(){

        ObjEmp obj;
        if(this.nbObj <= 0){
            System.out.println("CANNOT POP, nbOBJ is" + this.nbObj);
            obj = null;
        } else {
            obj = this.stack[this.nbObj -1];
            this.nbObj--;
        }
        return obj;
    }


    public int getSize(){
        return this.stack.length;
    }

    public int getCount(){
        return this.nbObj;
    }

    public void push(ObjEmp objEmp){

        if(this.nbObj == this.stack.length){
            System.out.println("Stack is full, cannot push.");
        }

        this.stack[this.nbObj] = objEmp;
        this.nbObj++;
        return;
    }

/*
    public String toString(){

        if(this.nbObj == 0){
            return "src.PileRPL is empty";
        }
        String str = "PileRpl count<"+ this.nbObj +"> and containe( ";
        for (int i = 0; i < this.nbObj; i++) {
            if( this.stack[i] != null ) {
                str += " ";
                str += this.stack[i];
            }
        }
        return str + " )";
    }
*/
    private int getLongestLengthObjEmp(){
        int lengthLongestObj = 0;
        for(int i = 0; i < this.nbObj; i++)
            lengthLongestObj = this.stack[i].toString().length() > lengthLongestObj ? this.stack[i].toString().length() : lengthLongestObj;
        return 10;
    }

    public String toString(){
        String str = "";
        // print empty space in array
        str += "\n+----------+";
        str += "\n!          !";
        
        for(int i = 0; i < this.nbObj; i++){

            String strValue = this.stack[i].getA() + " " + this.stack[i].getB();
            String stringPadding = "";
            for(int a = 0; a < 10 - strValue.length(); a++) stringPadding += " ";
            str += ("\n!" + strValue + stringPadding + "!");
        }

        // print tab enf line

        str += ("\n+----------+");
        return str;
    }

}
