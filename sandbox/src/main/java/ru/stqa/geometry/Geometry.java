package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimetr);

        //Triangle.printTriangleArea(new Triangle(10, 10, 3));
       // Triangle.printTriangleArea(new Triangle(-6, 10, 20));

       // Rectangle.printRectangleArea(3.0 , 7.0);



    }

}
