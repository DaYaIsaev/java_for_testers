package ru.stqa.geometry.figures;

public class Square {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea (Square s){
        System.out.println(String.format("Площадь квадрата со сторой %f = %f", s.side, s.area()));
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter(){
        return 4 * this.side;
    }


}
