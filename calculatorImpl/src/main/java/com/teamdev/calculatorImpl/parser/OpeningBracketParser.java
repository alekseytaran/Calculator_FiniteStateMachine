package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class OpeningBracketParser implements ExpressionParser {

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        reader.getRemainingExpression();
        reader.getPosition();

        reader.movePosition("(".length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationStack outputContext) {
                Deque<Double> operandStack = new ArrayDeque<>();
                Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
                outputContext.getListOperandStack().push(operandStack);
                outputContext.getListOperatorStack().push(operatorStack);
            }
        };
    }
}
