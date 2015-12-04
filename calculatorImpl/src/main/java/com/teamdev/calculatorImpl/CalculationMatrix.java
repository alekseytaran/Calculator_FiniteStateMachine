package com.teamdev.calculatorImpl;

import com.teamdev.fsm.TransitionMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.teamdev.calculatorImpl.CalculationState.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;

public class CalculationMatrix implements TransitionMatrix<CalculationState> {

    private Map<CalculationState, Set<CalculationState>> transitions = new HashMap<
            CalculationState, Set<CalculationState>>() {{

        put(START, of(CODE_LINE));
        put(CODE_LINE, of(FUNCTION, WRITE_VARIABLE, OPENING_BRACKET));
        put(WRITE_VARIABLE, of(ASSIGN_VALUE));
        put(ASSIGN_VALUE, of(EXPRESSION, FUNCTION));
        put(EXPRESSION, of(NUMBER, OPENING_BRACKET, FUNCTION, READ_VARIABLE));
        put(READ_VARIABLE, of(BINARY_OPERATOR, ARGUMENT_DELIMITER, CLOSING_BRACKET, END_OF_LINE));
        put(END_OF_LINE, of(CODE_LINE, FINISH));
        put(NUMBER, of(BINARY_OPERATOR, ARGUMENT_DELIMITER, CLOSING_BRACKET, END_OF_LINE));
        put(BINARY_OPERATOR, of(NUMBER, READ_VARIABLE, OPENING_BRACKET, FUNCTION));
        put(OPENING_BRACKET, of(NUMBER, READ_VARIABLE, OPENING_BRACKET, FUNCTION));
        put(CLOSING_BRACKET, of(BINARY_OPERATOR, ARGUMENT_DELIMITER, CLOSING_BRACKET, END_OF_LINE));
        put(FUNCTION, of(OPENING_BRACKET));
        put(ARGUMENT_DELIMITER, of(NUMBER, READ_VARIABLE, OPENING_BRACKET, FUNCTION));
        put(FINISH, noneOf(CalculationState.class));
    }};

    @Override
    public CalculationState getStartState() {
        return START;
    }

    @Override
    public CalculationState getFinishState() {
        return FINISH;
    }

    @Override
    public Set<CalculationState> getPossibleTransitions(CalculationState calculationState) {

        final Set<CalculationState> result = transitions.get(calculationState);

        while (hasAggregateState(result)) {
            for (CalculationState state : result) {
                if (state.isAggregate()) {
                    result.remove(state);
                    result.addAll(transitions.get(state));
                }
            }
        }

        return result;
    }

    private boolean hasAggregateState(Set<CalculationState> result) {
        for (CalculationState state : result) {
            if (state.isAggregate()) {
                return true;
            }
        }
        return false;
    }
}
