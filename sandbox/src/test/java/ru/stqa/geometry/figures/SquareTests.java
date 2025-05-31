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
}
