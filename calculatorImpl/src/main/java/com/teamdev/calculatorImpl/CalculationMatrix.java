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
        put(START, of(NUMBER, OPENING_BRACKET));
        put(NUMBER, of(BINARY_OPERATOR, FINISH, CLOSING_BRACKET));
        put(OPENING_BRACKET, of(NUMBER));
        put(CLOSING_BRACKET, of(CLOSING_BRACKET, BINARY_OPERATOR, FINISH));
        put(BINARY_OPERATOR, of(NUMBER, OPENING_BRACKET));
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
        return transitions.get(calculationState);
    }
}
