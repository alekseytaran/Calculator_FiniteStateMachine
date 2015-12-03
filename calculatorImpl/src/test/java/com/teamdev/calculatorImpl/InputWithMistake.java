package com.teamdev.calculatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputWithMistake {
    private MathExpressionCalculator calculator;

    @Before
    public void beforeTest() {
        calculator = new MathExpressionCalculator();
    }

    @After
    public void destroyInstances() {
        calculator = null;
    }

    @Test(expected = RuntimeException.class)
    public void testOddClosingBracket() throws CalculationError {
        calculator.calculate("2.4 + 2.6)");
    }

    @Test(expected = RuntimeException.class)
    public void testOddOpeningBracket() throws CalculationError {
        calculator.calculate("(2.4 + 2.6");
    }

    @Test(expected = RuntimeException.class)
    public void testSkipBinaryOperator() throws CalculationError {
        calculator.calculate("(2.4  2.6 + 3");
    }

    @Test(expected = RuntimeException.class)
    public void testSkipNumber() throws CalculationError {
        calculator.calculate("(2.4 + + 4");
    }

    @Test(expected = CalculationError.class)
    public void testEmptyBrackets() throws CalculationError {
        calculator.calculate("3+ () + 2.4 + + 4");
    }

    @Test(expected = CalculationError.class)
    public void testEmptyString() throws CalculationError {
        calculator.calculate("");
    }

    @Test(expected = CalculationError.class)
    public void testOneBracket() throws CalculationError {
        calculator.calculate("(");
    }



}
