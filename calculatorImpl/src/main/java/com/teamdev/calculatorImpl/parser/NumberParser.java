package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.EvaluationCommand;
import com.teamdev.calculatorImpl.EvaluationStack;
import com.teamdev.calculatorImpl.ExpressionParser;
import com.teamdev.calculatorImpl.MathExpressionReader;

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
            public void execute(EvaluationStack outputContext) {
                outputContext.getListOperandStack().peek().push(result.doubleValue());
            }
        };
    }
}
