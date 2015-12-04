package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class NumberParser implements ExpressionParser {

    @Override
    public EvaluationCommand accept(MathExpressionReader reader) {

        if (!reader.hasMoreElements()) {
            return null;
        }

        final NumberFormat numberFormat = new DecimalFormat("0.0");

        final ParsePosition parsePosition = new ParsePosition(0);

        final Number result =
                numberFormat.parse(reader.getRemainingExpression(), parsePosition);

        if (parsePosition.getErrorIndex() > -1) {
            return null;
        }

        final int parseIndex = parsePosition.getIndex();

        reader.movePosition(parseIndex);

        return new EvaluationCommand() {
            @Override
            public void execute(EvaluationContext outputContext) {
                outputContext.getEvaluationStack().getOperandStack().push(result.doubleValue());
            }
        };
    }
}
