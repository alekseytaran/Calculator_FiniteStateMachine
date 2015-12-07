package com.teamdev.calculatorImpl.function;

import java.util.Optional;

public class Out extends AbstractFunction {

    public Out() {
        super(1, ANY_ARGUMENTS);
    }

    @Override
    public Optional<Double> execute(double... arguments) {

        for (double argument: arguments) {
            System.out.println(argument);
        }

        return Optional.empty();
    }
}