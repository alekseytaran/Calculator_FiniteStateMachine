package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;

import java.util.Deque;
import java.util.NoSuchElementException;

public class ClosingBracketParser implements ExpressionParser {

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        final String expression = reader.getRemainingExpression();

        final String presentation = ")";
        reader.movePosition(presentation.length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationStack outputContext){

                final Deque<Double> operandStack = outputContext.getListOperandStack().pop();
                final Deque<BinaryOperator> operatorsStack = outputContext.getListOperatorStack().pop();
                while (!operatorsStack.isEmpty()) {
                    BinaryOperator topOfStackOperator = operatorsStack.pop();
                    final Double rightOperand = operandStack.pop();
                    final Double leftOperand = operandStack.pop();
                    final Double result = topOfStackOperator.execute(leftOperand, rightOperand);
                    outputContext.getListOperandStack().peek().push(result);
                }
            }
        };
    }
}
