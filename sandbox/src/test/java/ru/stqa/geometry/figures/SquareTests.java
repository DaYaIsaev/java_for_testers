package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        Assertions.assertEquals( 25.0, s.area());
    }
    @Test
    void canCalculatePerimeter(){
        var s = new Square (5.0);
        Assertions.assertEquals(20, s.perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5);
            Assertions.fail();
        } catch (IllegalArgumentException exeption) {
            System.out.println(exeption);

        }
    }
    @Test
    void testEquality(){
        var s1 = new Square(5);
        var s2 = new Square(5);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality(){
        var s1 = new Square(6);
        var s2 = new Square(5);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testFail(){
        var s1 = new Square(6);
        var s2 = new Square(6);
        Assertions.assertTrue(s1.equals(s2));
    }
}
