package com.teamdev.calculatorImpl;

import com.teamdev.calculatorImpl.parser.ExpressionParserFactory;
import com.teamdev.fsm.AbstractFiniteStateMachine;

public class MathExpressionCalculator extends AbstractFiniteStateMachine<

        MathExpressionReader,
        EvaluationStack,
        CalculationState,
        EvaluationCommand,
        ExpressionParser,
        CalculationMatrix,
        CalculationError,
        Double>

        implements Calculator {

    final private ExpressionParserFactory parserFactory = new ExpressionParserFactory();
    final private CalculationMatrix matrix = new CalculationMatrix();

    @Override
    public double calculate(String expression) throws CalculationError {
        return run(new MathExpressionReader(expression), new EvaluationStack());
    }


    @Override
    protected Double prepareResult(EvaluationStack context) {
        return context.getOperandStack().pop();
    }

    @Override
    protected void deadlock(MathExpressionReader context) throws CalculationError {
        throw new CalculationError("state doesn't exist");
    }

    @Override
    protected ExpressionParser getStateAcceptor(CalculationState state) {
        return parserFactory.getParser(state);
    }

    @Override
    protected CalculationMatrix getTransitionMatrix() {
        return matrix;
    }

    public static void main(String[] args) throws Exception {
        final MathExpressionCalculator calculator = new MathExpressionCalculator();
        final double result = calculator.calculate("2+3 *4)");
        System.out.println("result = " + result);
    }
}
