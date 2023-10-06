import java.lang.*;
import java.io.*;


class PileRPL {

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
        this.push(obj1.add(obj2));
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


    public String toString(){

        if(this.nbObj == 0){
            return "PileRPL is empty";
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

}
