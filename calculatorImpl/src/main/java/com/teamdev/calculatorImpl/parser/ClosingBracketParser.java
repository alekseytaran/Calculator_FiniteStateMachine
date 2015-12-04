package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;

public class ClosingBracketParser implements ExpressionParser {

    public static final String CLOSING_BRACKET = ")";

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        if (!reader.getRemainingExpression().startsWith(CLOSING_BRACKET)) {
            return null;
        }

        reader.movePosition(CLOSING_BRACKET.length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {
                outputContext.closeCurrentContext();
            }
        };
    }
}
