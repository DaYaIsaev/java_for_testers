package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (double a,
                        double b ,
                        double c) {

public Triangle {
    if (a < 0 || b < 0 || c <0){
        throw new IllegalArgumentException("Triangle side should be non-negative" );
    }
    if ( !(a + b > c && a + c > b && b + c > a)){
        throw new IllegalArgumentException("The inequality of the sides of a triangle is not satisfied");
    }
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0;



    }

    @Override
    public int hashCode() {
        return 1;
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
