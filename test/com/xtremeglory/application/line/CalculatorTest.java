package com.xtremeglory.application.line;

import org.junit.Assert;
import org.junit.Test;
import org.omg.CORBA.IntHolder;

public class CalculatorTest {
    @Test
    public void test() {
        Assert.assertEquals(Calculator.calc("2+3+9-3*3"), 5.0, 1e-7);
        Assert.assertEquals(Calculator.calc("2+3*(4-3)*3"), 11.0, 1e-7);
    }

    @Test
    public void testMatchSymbol() {
        IntHolder begin = new IntHolder(0);
        Assert.assertNull(Expression.matchSymbol("+5", begin));
        Assert.assertEquals(begin.value, 0);
        Assert.assertEquals(begin.value, 0);
    }
}
