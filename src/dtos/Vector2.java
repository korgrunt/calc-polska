package src.dtos;

import java.lang.*;

public class Vector2  {

    private int a;
    private int b;

    public Vector2(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Vector2 add(Vector2 obj) {
        return new Vector2(this.a + obj.getA(), this.b + obj.getB());
    }

    public Vector2 substract(Vector2 obj) {
        return new Vector2(this.a - obj.getA(), this.b - obj.getB());
    }

    public Vector2 multiply(Vector2 obj) {
        return new Vector2((this.a * obj.getB()) - (this.b * obj.getA()),
                (this.a * obj.getB()) - (obj.getA() * this.b));
    }

    public Vector2 divide(Vector2 obj) {
        return new Vector2(this.a / obj.getA(), this.b / obj.getB());
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public String toString() {
        return "{src.ObjEmp => a = " + this.a + ", b = " + this.b + "}";
    }
}
