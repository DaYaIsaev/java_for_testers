package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void calculateArea() {
        var t = new Triangle(5, 4, 3);
        Assertions.assertEquals(6, t.area());
    }

    @Test
    void calculatePerimeter() {
        var t = new Triangle(5, 4, 3);
        Assertions.assertEquals(12, t.perimeter());

    }

    @Test
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new Triangle(-5, 4, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exeption) {

        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(5, -4, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exeption) {

        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(5, 4, -3);
            Assertions.fail();
        } catch (IllegalArgumentException exeption) {

        }
    }

    @Test
    void testEquals(){
        var t1 = new Triangle(5,4,3);
        var t2 = new Triangle (3,4,5);
        Assertions.assertEquals(t1, t2);

    }

    @Test
    void testEquals1(){
        var t1 = new Triangle(5,4,3);
        var t2 = new Triangle (5,4,3);
        Assertions.assertEquals(t1, t2);

    }

    @Test
    void testEquals2(){
        var t1 = new Triangle(5,4,3);
        var t2 = new Triangle (4,5,3);
        Assertions.assertEquals(t1, t2);

    }

    @Test
    void testEquals3(){
        var t1 = new Triangle(5,4,3);
        var t2 = new Triangle (3,4,5);
        Assertions.assertEquals(t1, t2);

    }

}




