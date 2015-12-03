package com.teamdev.calculatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MathExpressionCalculatorTest {

    private MathExpressionCalculator calculator;

    @Before
    public void beforeTest() {
        calculator = new MathExpressionCalculator();
    }

    @After
    public void destroyInstances() {
        calculator = null;
    }

    @Test
    public void testPlusOperation() throws CalculationError {
        final double result = calculator.calculate("2.4 + 2.6");
        assertEquals(5.0, result);
    }

    @Test
    public void testMultiplyOperation() throws CalculationError {
        final double result = calculator.calculate("2 * 3");
        assertEquals(6.0, result);
    }

    @Test
    public void testSkipSpaces() throws CalculationError {
        final double resultWithoutSkip  = calculator.calculate("2*(3+2)");
        final double resultWithSkip = calculator.calculate("2 * (3 + 2)");
        assertEquals(resultWithoutSkip, resultWithSkip);
    }

    @Test
    public void testBracketExpression() throws CalculationError {
        final double result = calculator.calculate("2 * (3 + 2)");
        assertEquals(10.0, result);
    }

    @Test
    public void testNestedBracketExpression() throws CalculationError {
        final double result = calculator.calculate("2 * (3 + (1+1))");
        assertEquals(10.0, result);
    }

    @Test
    public void testOperatorPriority() throws CalculationError {
        final double result = calculator.calculate("2+3 *4 + 2^2");
        assertEquals(18.0, result);
    }
}
