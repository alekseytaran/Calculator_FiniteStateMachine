package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.EvaluationCommand;
import com.teamdev.calculatorImpl.EvaluationContext;
import com.teamdev.calculatorImpl.ExpressionParser;
import com.teamdev.calculatorImpl.MathExpressionReader;

public class EndOfExpressionParser implements ExpressionParser {

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (reader.hasMoreElements()) {
            return null;
        }

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {
                outputContext.getEvaluationStack().popAllOperators();
            }
        };
    }
}
