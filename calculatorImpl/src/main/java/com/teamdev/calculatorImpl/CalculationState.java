package com.teamdev.calculatorImpl;

import com.teamdev.fsm.MachineState;

public enum CalculationState implements MachineState {

    START,
    NUMBER,
    BINARY_OPERATOR,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    FUNCTION,
    ARGUMENT_DELIMITER,
    WRITE_VARIABLE,
    READ_VARIABLE,
    ASSIGN_VALUE,
    EXPRESSION(true),
    CODE_LINE(true),
    END_OF_LINE,
    FINISH;

    private boolean aggregate = false;

    CalculationState() {
    }

    CalculationState(boolean aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean isAggregate() {
        return aggregate;
    }
}
