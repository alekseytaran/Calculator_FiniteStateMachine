package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.EvaluationCommand;
import com.teamdev.calculatorImpl.EvaluationContext;
import com.teamdev.calculatorImpl.ExpressionParser;
import com.teamdev.calculatorImpl.MathExpressionReader;

public class EndOfLineParser implements ExpressionParser {

    public static final String DELIMITER = ";";

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        if (!reader.getRemainingExpression().startsWith(DELIMITER)) {
            return null;
        }

        reader.movePosition(DELIMITER.length());

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {

                if (outputContext.getEvaluationStack().getParent() != null) {
                    outputContext.closeCurrentContext();
                }
            }
        };
    }
}
