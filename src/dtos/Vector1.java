package src.dtos;

public class Vector1 {

    private int a;

    public Vector1(int a){
        this.a = a;
    }

    public Vector1 add(Vector1 obj) {
        return new Vector1(this.a + obj.getA());
    }

    public Vector1 substract(Vector1 obj){
        return new Vector1(this.a - obj.getA());
    }

    public Vector1 multiply(Vector1 obj){
        return new Vector1(this.a * obj.getA());
    }

    public Vector1 divide(Vector1 obj){
        return new Vector1(this.a / obj.getA());
    }
    public int getA(){
        return this.a;
    }

    public void setA(int a){
        this.a = a;
    }

    public String toString(){
        return "{src.ObjEmp => a = "+this.a+" }";
    }
}
