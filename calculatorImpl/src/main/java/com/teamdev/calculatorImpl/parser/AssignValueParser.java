package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.EvaluationCommand;
import com.teamdev.calculatorImpl.EvaluationContext;
import com.teamdev.calculatorImpl.ExpressionParser;
import com.teamdev.calculatorImpl.MathExpressionReader;

public class AssignValueParser implements ExpressionParser {

    public static final String ASSIGN = "=";

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        if (!reader.getRemainingExpression().startsWith(ASSIGN)) {
            return null;
        }

        reader.movePosition(ASSIGN.length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {
                outputContext.enterNewContext();
            }
        };
    }
}
