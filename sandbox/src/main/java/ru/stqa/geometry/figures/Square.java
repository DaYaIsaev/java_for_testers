package ru.stqa.geometry.figures;

public record Square (double side) {

    public Square {
        if (side < 0 ) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }


    public static void printSquareArea (Square s){
        System.out.println(String.format("Площадь квадрата со сторой %f = %f", s.side, s.area()));
    }
    public static void printSquarePerimetr (Square s) {
        System.out.println(String.format("Периметр квадрата со сторой %f = %f", s.side, s.perimeter()));
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter(){
        return 4 * this.side;
    }


}
