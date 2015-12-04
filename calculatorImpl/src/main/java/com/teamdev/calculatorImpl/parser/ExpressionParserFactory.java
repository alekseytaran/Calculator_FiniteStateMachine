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
                put(OPENING_BRACKET, new OpeningBracketParser());
                put(CLOSING_BRACKET, new ClosingBracketParser());
                put(FUNCTION, new FunctionParser());
                put(ARGUMENT_DELIMITER, new ArgumentDelimiterParser());
                put(END_OF_LINE, new EndOfLineParser());

                put(READ_VARIABLE, new ReadVariableParser());
                put(WRITE_VARIABLE, new WriteVariableParser());
                put(ASSIGN_VALUE, new AssignValueParser());

                put(FINISH, new EndOfExpressionParser());
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
