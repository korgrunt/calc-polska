import java.lang.*;

class PileRPL {

    ObjEmp[] stack;
    int maxSize;

    public PileRPL(int maxSize){
        this.maxSize = maxSize;
        this.stack = new ObjEmp[this.maxSize];
        
    }


    public void push(ObjEmp e){


    }

    public int getSize(){
        return this.stack.length;
    }

    public int getCount(){
        int count = 0;
        while(count < this.stack.length) {
            
            System.out.println("elm number " + count + "in stack is equal => below");
            System.out.println(this.stack[count]);

            if(this.stack[count] == null){
                return count;
            }
            count++;
        }
        return 2;
    }



    public void add(){

        if(this.stack.length == this.maxSize){
            System.out.println("Stack is full, cannot add.");
            return;
        }

        return;

    
        
    }


    public String toString(){

        if(this.stack.length == 0){
            return "PileRPL is empty";
        }
        String str = "";
        for (int i = 0; i < this.stack.length; i++) {
            str += this.stack[i];
        }
        return str;
    }


}
