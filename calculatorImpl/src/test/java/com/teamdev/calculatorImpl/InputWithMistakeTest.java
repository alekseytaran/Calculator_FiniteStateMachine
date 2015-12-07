package com.teamdev.calculatorImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

public class InputWithMistakeTest {
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

    public void checkMessageAndPositionError(String expression, String errorMessage, int position) {
        try {
            MathExpressionReader expressionReader = new MathExpressionReader(expression);
            calculator.run(expressionReader, context);

            fail("error wasn't appeared");
        } catch (CalculationError e) {

            assertEquals(errorMessage, e.getMessage());
            assertEquals("Position doesn't correct", position, e.getErrorPosition()+1);
        }
    }

    @Test
    public void testEmptyVariable() throws CalculationError {
        checkMessageAndPositionError("var=;", "Necessary state wasn't found", 5);

    }

    @Test
    public void testOpenCodeLine() throws CalculationError {
        checkMessageAndPositionError("var=(2+2)", "Necessary state wasn't found", 10);

    }

    @Test
    public void testTwoAssignState() throws CalculationError {
        checkMessageAndPositionError("var==(2+3);", "Necessary state wasn't found", 5);
    }

    @Test
    public void testOddClosingBracket() throws CalculationError {
        checkMessageAndPositionError("2.4 + 2.6)", "Necessary state wasn't found", 1);
    }

    @Test
    public void testOddOpeningBracket() throws CalculationError {
        checkMessageAndPositionError("(2.4 + 2.6", "Necessary state wasn't found", 11);
    }

    @Test
    public void testSkipBinaryOperator() throws CalculationError {
        checkMessageAndPositionError("(2.4  2.6", "Necessary state wasn't found", 7);
    }

    @Test
    public void testSkipNumber() throws CalculationError {
        checkMessageAndPositionError("(2.4 + + 4", "Necessary state wasn't found", 8);
    }

    @Test
    public void testEmptyBrackets() throws CalculationError {
        checkMessageAndPositionError("()", "Necessary state wasn't found", 2);
    }

    @Test
    public void testEmptyString() throws CalculationError {
        checkMessageAndPositionError("", "Necessary state wasn't found", 1);
    }

    @Test
    public void testOneOpenBracket() throws CalculationError {
        checkMessageAndPositionError("(", "Necessary state wasn't found", 2);
    }

    @Test
    public void testOneCloseBracket() throws CalculationError {
        checkMessageAndPositionError(")", "Necessary state wasn't found", 1);
    }

    @Test
    public void testMissingBinaryOperator() {
        checkMessageAndPositionError("var=(2 2);", "Necessary state wasn't found", 8);
    }

    @Test
    public void testMissingNumber() {
        checkMessageAndPositionError("var=(+ 2);", "Necessary state wasn't found", 6);
    }

    @Test
    public void testMissingAssignValue() {
        checkMessageAndPositionError("var(2+ 2);", "Necessary state wasn't found", 4);
    }

    @Test
    public void testNotCloseCodeLine() {
        checkMessageAndPositionError("var=(2+ 2)", "Necessary state wasn't found", 11);
    }


}
