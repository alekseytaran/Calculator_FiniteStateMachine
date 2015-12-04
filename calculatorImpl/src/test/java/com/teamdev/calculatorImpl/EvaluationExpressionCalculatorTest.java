package com.teamdev.calculatorImpl;

import com.teamdev.fsm.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class EvaluationExpressionCalculatorTest {

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
    public void testPlusOperationInVariable() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var= (2.25+2.75);");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 5.0, actualResult);
    }

    @Test
    public void testMultiplyOperationInVariable() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var= (2.5* 2);");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 5.0, actualResult);
    }

    @Test
    public void testSkipSpaces() throws CalculationError {
        calculator.run(new MathExpressionReader("a = 2; b = a + 2;"), context);
        double resultWithSpaces = context.readVariable("b");
        calculator.run(new MathExpressionReader("a=2;b=a+2;"), context);
        double resultWithoutSpaces = context.readVariable("b");
        assertEquals("Spaces fluent on the same phrases", resultWithoutSpaces, resultWithSpaces);
    }

    @Test
    public void testBracketExpression() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var = 2+(3+ 2) *2;");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 12.0, actualResult);
    }


    @Test
    public void testNestedBracketExpression() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var = 2+(3+ (1+1)) *2;");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 12.0, actualResult);
    }

    @Test
    public void testOperatorPriority() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var = 2 + 3 * 2^2;");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 14.0 , actualResult);
    }

    @Test
    public void testPlusVariables() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("a=2;b=2.5;var=a+b;");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 4.5 , actualResult);
    }

    @Test
    public void testMultiplyVariables() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("a=2;b=3;var=a*b;");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 6.0 , actualResult);
    }

    @Test
    public void testSumFunction() throws CalculationError {
        MathExpressionReader expressionReader = new MathExpressionReader("var=sum(2,3);");
        calculator.run(expressionReader, context);
        double actualResult = context.readVariable("var");
        assertEquals("Variable 'var' doesn't equal expected result", 5.0 , actualResult);
    }
}
