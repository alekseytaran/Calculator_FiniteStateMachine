package com.teamdev.calculatorImpl;

import com.teamdev.fsm.StateAcceptor;

public interface ExpressionParser extends StateAcceptor<
        MathExpressionReader,
        EvaluationContext,
        EvaluationCommand,
        CalculationError> {

    @Override
    EvaluationCommand accept(MathExpressionReader reader) throws CalculationError;
}
