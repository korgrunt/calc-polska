package src.dtos;

public class Vector3  {

    private int a;
    private int b;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    private int c;

    public Vector3(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Vector3 add(Vector3 obj) {
        return new Vector3(this.a + obj.getA(), this.b + obj.getB(), this.c + obj.getC());
    }

    public Vector3 substract(Vector3 obj) {
        return new Vector3(this.a - obj.getA(), this.b - obj.getB(), this.c - obj.getC());
    }

    public Vector3 multiply(Vector3 obj) {

        return new Vector3(
                this.b * obj.getC() - this.c * obj.getB(),
                this.c * obj.getA() - this.a * obj.getC(),
                this.a * obj.getB() - this.b * obj.getA());
    }

    public Vector3 divide(Vector3 obj) {

        return new Vector3(
                obj.getA() / this.a,
                obj.getB() / this.b,
                obj.getC() / this.c);
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String toString() {
        return "{src.ObjEmp => a = " + this.a + ", b = " + this.b + ", c= " + this.c + "}";
    }
}
