package com.teamdev.calculatorImpl.function;

import java.util.Optional;

public class Sum extends AbstractFunction {

    public Sum() {
        super(2, ANY_ARGUMENTS);
    }

    @Override
    public Optional<Double> execute(double... arguments) {
        double result = 0;
        for (double value : arguments) {
            result += value;
        }
        return Optional.of(result);
    }
}
