import java.lang.*;

class ObjEmp {

    private int a;
    private int b;

    public ObjEmp(int a, int b){

        this.a = a;
        this.b = b;

    }


    public ObjEmp add(ObjEmp obj){
        ObjEmp newObj = new ObjEmp(this.a + obj.getA(), this.b + obj.getB());
        return newObj; 
    }

    public ObjEmp substract(ObjEmp obj){
        ObjEmp newObj = new ObjEmp(this.a - obj.getA(), this.b - obj.getB());
        return newObj; 
    }

    public static ObjEmp parseInput(String str){
        ObjEmp objEmp = null;

        String[] numbers = str.split(" ");

        if(numbers.length == 2){

            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);

            objEmp = new ObjEmp(a, b);
        }
        System.out.println(objEmp);
        return objEmp;
    }

    public int getA(){
        return this.a;
    }

    public int getB(){
        return this.b;
    }

    public void setA(int a){
        this.a = a;
    }

    public void setB(int b){
        this.b = b;
    }

    public String toString(){

        return "{ObjEmp => a = "+this.a+", b = "+this.b+"}";

    }


}
