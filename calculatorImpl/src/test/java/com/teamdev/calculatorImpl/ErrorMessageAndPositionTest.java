package com.teamdev.calculatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ErrorMessageAndPositionTest {
    private MathExpressionCalculator calculator;
    private EvaluationContext context;

    @Before
    public void beforeTest() {
        calculator = new MathExpressionCalculator();
        context = new EvaluationContext();
    }

    @After
    public void destroyInstances() {
        calculator = null;
        context = null;
    }

    @Test
    public void testMissingBinaryOperator() {

        try {
            MathExpressionReader expressionReader = new MathExpressionReader("var=(2 2);");
            calculator.run(expressionReader, context);
        } catch (CalculationError e) {

            assertEquals("Necessary state wasn't found", e.getMessage());
            assertEquals("Position doesn't correct", 8, e.getErrorPosition()+1);
        }
    }

    @Test
    public void testMissingNumber() {

        try {
            MathExpressionReader expressionReader = new MathExpressionReader("var=(+ 2);");
            calculator.run(expressionReader, context);
        } catch (CalculationError e) {

            assertEquals("Necessary state wasn't found", e.getMessage());
            assertEquals("Position doesn't correct", 6, e.getErrorPosition()+1);
        }
    }


    @Test
    public void testMissingAssignValue() {

        try {
            MathExpressionReader expressionReader = new MathExpressionReader("var(2+ 2);");
            calculator.run(expressionReader, context);
        } catch (CalculationError e) {

            assertEquals("Necessary state wasn't found", e.getMessage());
            assertEquals("Position doesn't correct", 4, e.getErrorPosition()+1);
        }
    }

    @Test
    public void testNotCloseCodeLine() {

        try {
            MathExpressionReader expressionReader = new MathExpressionReader("var=(2+ 2)");
            calculator.run(expressionReader, context);
        } catch (CalculationError e) {

            assertEquals("Necessary state wasn't found", e.getMessage());
            assertEquals("Position doesn't correct", 11, e.getErrorPosition()+1);
        }
    }
}

