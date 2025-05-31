package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTest {

    @Test
    void testEquality(){
        var r1 = new Rectangle(5,8);
        var r2 = new Rectangle(5,8);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2(){
        var r1 = new Rectangle(5,8);
        var r2 = new Rectangle(8,5);
        Assertions.assertEquals(r1, r2);
    }


}

