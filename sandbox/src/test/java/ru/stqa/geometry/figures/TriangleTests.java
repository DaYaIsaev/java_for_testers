package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void calculateArea (){
        var t = new Triangle(5,4,3);
        Assertions.assertEquals(6, t.area());
    }

    @Test
    void calculatePerimeter (){
        var t = new Triangle(5,4,3);
        Assertions.assertEquals(12, t.perimeter());

    }
}
