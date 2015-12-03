package com.teamdev.calculatorImpl;

import com.teamdev.fsm.StateTransitionCommand;

public interface EvaluationCommand extends StateTransitionCommand<EvaluationStack> {

    @Override
    void execute(EvaluationStack outputContext);
}
