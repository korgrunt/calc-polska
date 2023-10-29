package src.dtos;

public class Vector {

    private Integer a;
    private Integer b;
    private Integer c;

    public Vector(Integer a){
        this.a = a;
    }
    public Vector(Integer a, Integer b){
        this.a = a;
        this.b = b;
    }
    public Vector(Integer a, Integer b, Integer c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean areNotNull(Integer... args){
        boolean areNotNull = true;
        for(Integer arg : args){
            if(arg == null){
                areNotNull = false;
            }
        }
        return areNotNull;
    }
    public Vector add(Vector obj) {
        Vector vector = null;
        if(areNotNull(obj.getA(), obj.getB(), obj.getC(), this.a, this.b, this.c)){
            vector = new Vector(this.a + obj.getA(), this.b + obj.getB(),this.c + obj.getC());
        } else if(areNotNull(obj.getA(), obj.getB(), this.a, this.b)){
            vector = new Vector(this.a + obj.getA(), this.b + obj.getB());
        } else if(areNotNull(obj.getA(), this.a)){
            vector = new Vector(this.a + obj.getA());
        }
        return vector;
    }

    public Vector substract(Vector obj){
        Vector vector = null;
        if(areNotNull(obj.getA(), obj.getB(), obj.getC(), this.a, this.b, this.c)){
            vector = new Vector(this.a - obj.getA(), this.b - obj.getB(),this.c - obj.getC());
        } else if(areNotNull(obj.getA(), obj.getB(), this.a, this.b)){
            vector = new Vector(this.a - obj.getA(), this.b - obj.getB());
        } else if(areNotNull(obj.getA(), this.a)){
            vector = new Vector(this.a - obj.getA());
        }
        return vector;
    }

    public Vector multiply(Vector obj){
        Vector vector = null;
        if(areNotNull(obj.getA(), obj.getB(), obj.getC(), this.a, this.b, this.c)){
            vector = new Vector(
                    this.b * obj.getC() - this.c * obj.getB(),
                    this.c * obj.getA() - this.a * obj.getC(),
                    this.a * obj.getB() - this.b * obj.getA());
        } else if(areNotNull(obj.getA(), obj.getB(), this.a, this.b)){
            vector = new Vector((this.a * obj.getB()) - (this.b * obj.getA()),
                    (this.a * obj.getB()) - (obj.getA() * this.b));
        } else if(areNotNull(obj.getA(), this.a)){
            vector = new Vector(this.a * obj.getA());
        }
        return vector;
    }

    public Vector divide(Vector obj){
        Vector vector = null;
        if(areNotNull(obj.getA(), obj.getB(), obj.getC(), this.a, this.b, this.c)){
            vector = new Vector(
                    obj.getA() / this.a,
                    obj.getB() / this.b,
                    obj.getC() / this.c);
        } else if(areNotNull(obj.getA(), obj.getB(), this.a, this.b)){
            vector = new Vector(this.a / obj.getA(), this.b / obj.getB());
        } else if(areNotNull(obj.getA(), this.a)){
            vector = new Vector(this.a / obj.getA());
        }
        return vector;
    }
    public Integer getA(){
        return this.a;
    }

    public Integer getB(){
        return this.b;
    }

    public Integer getC(){
        return this.c;
    }



    public String toString(){
        String objToString = null;
        if(areNotNull(this.a, this.b, this.c)){
            objToString = this.a + " " + this.b + " " + this.c;
        } else if(areNotNull(this.a, this.b)){
            objToString = this.a + " " + this.b;
        } else if(areNotNull(this.a)){
            objToString = this.a + " " ;
        }
        return objToString;
    }
}
