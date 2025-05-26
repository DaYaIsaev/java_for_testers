package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea (double side){
        System.out.println(String.format("Площадь квадрата со сторой %f = %f", side, squareArea(side)));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
