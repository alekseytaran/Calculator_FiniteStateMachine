package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;
import com.teamdev.calculatorImpl.function.FunctionFactory;

import java.util.Deque;
import java.util.Optional;

public class FunctionParser implements ExpressionParser {

    private final FunctionFactory factory = new FunctionFactory();

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) throws CalculationError {

        if (!reader.hasMoreElements()) {
            return null;
        }

        final String expression = reader.getRemainingExpression();

        for (String presentation : factory.getAllPresentations()) {
            if (expression.startsWith(presentation)) {

                final Function function = factory.getFunction(presentation);

                reader.movePosition(presentation.length());

                return new EvaluationCommand() {
                    @Override
                    public void execute(EvaluationContext outputContext) {
                        outputContext.setActualCloser(new EvaluationContextCloser() {
                            @Override
                            public void closeContext(EvaluationStack stack) {

                                stack.popAllOperators();

                                final Deque<Double> operandStack = stack.getOperandStack();

                                final double[] arguments = new double[operandStack.size()];

                                int i = operandStack.size() - 1;
                                for (double value : operandStack) {
                                    arguments[i--] = value;
                                }

                                final Optional<Double> result = function.execute(arguments);

                                if (result.isPresent()) {
                                    stack.getParent().getOperandStack().push(result.get());
                                }

                            }
                        });
                    }
                };
            }
        }
        return null;
    }
}
