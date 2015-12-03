package com.teamdev.calculatorImpl.parser;

import com.teamdev.calculatorImpl.CalculationState;
import com.teamdev.calculatorImpl.ExpressionParser;

import java.util.HashMap;
import java.util.Map;

import static com.teamdev.calculatorImpl.CalculationState.*;

public class ExpressionParserFactory {

    private final Map<CalculationState, ExpressionParser> parsers =
            new HashMap<CalculationState, ExpressionParser>() {{

                put(NUMBER, new NumberParser());
                put(BINARY_OPERATOR, new BinaryOperatorParser());
                put(FINISH, new EndOfExpressionParser());
                put(OPENING_BRACKET, new OpeningBracketParser());
                put(CLOSING_BRACKET, new ClosingBracketParser());
            }};

    public ExpressionParser getParser(CalculationState state) {

        if (!parsers.containsKey(state)) {
            throw new RuntimeException("Parser not found for " +
                    CalculationState.class.getName() +
                    "." + state.toString());

        }

        return parsers.get(state);
    }
}
