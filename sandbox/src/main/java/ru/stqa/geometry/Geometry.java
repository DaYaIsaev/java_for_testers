package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {


        Square.printSquareArea(new Square(7));

        Triangle.printTriangleArea(new Triangle(5, 4, 3));

        //Triangle.printTriangleArea(new Triangle(10, 10, 3));

        Triangle.printTriangleArea(new Triangle(-6, 10, 20));


       // Rectangle.printRectangleArea(3.0 , 7.0);



    }

}
