package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteVariableParser implements ExpressionParser {

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        final String expression = reader.getRemainingExpression();

        final Pattern pattern = Pattern.compile("[a-z]+");

        final Matcher matcher = pattern.matcher(expression);

        if (!matcher.find()) {
            return null;
        }

        final String variableName = matcher.group(0);

        reader.movePosition(variableName.length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {
                outputContext.setActualCloser(new EvaluationContextCloser() {
                    @Override
                    public void closeContext(EvaluationStack stack) {

                        stack.popAllOperators();

                        outputContext.writeVariable(variableName,
                                stack.getOperandStack().pop());

                    }
                });
            }
        };
    }
}
