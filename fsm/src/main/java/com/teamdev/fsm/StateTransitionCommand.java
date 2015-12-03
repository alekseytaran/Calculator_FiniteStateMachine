package com.teamdev.fsm;

public interface StateTransitionCommand<
        Context extends OutputContext> {

    void execute(Context outputContext);

}
