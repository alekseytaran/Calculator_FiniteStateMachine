package com.teamdev.calculatorImpl.operator;

import com.teamdev.calculatorImpl.BinaryOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.teamdev.calculatorImpl.operator.AbstractBinaryOperator.Priority.*;

public class BinaryOperatorFactory {

    private final Map<String, BinaryOperator> operators =
            new HashMap<String, BinaryOperator>() {{

                put("+", new Plus(LOW));

                put("*", new Multiply(MEDIUM));

                put("^", new Power(HIGH));

            }};


    public BinaryOperator getBinaryOperator(String presentation) {
        return operators.get(presentation);
    }

    public Set<String> getAllPresentations() {
        return operators.keySet();
    }
}
