package ru.stqa.geometry.figures;

public record Triangle (double a,
                        double b ,
                        double c) {

public Triangle {
    if (a < 0 || b < 0 || c <0){
        throw new IllegalArgumentException("Triangle side should be non-negative" );

    }
}
    public static void printTriangleArea (Triangle t){
        System.out.println(String.format("Площадь квадрата со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.area()));
    }

    public double area(){

        var semiperimeter= perimeter()/2;
        return Math.sqrt(semiperimeter*(semiperimeter - this.a)*(semiperimeter - this.b)*(semiperimeter - this.c));

    }

    public double perimeter() {
      return this.a + this.b + this.c;
    }
}
