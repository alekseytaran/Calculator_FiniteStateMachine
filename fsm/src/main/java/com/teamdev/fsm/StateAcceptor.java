package com.teamdev.fsm;


public interface StateAcceptor<

        Input extends InputContext,
        Output extends OutputContext,
        Command extends StateTransitionCommand<Output>> {

    Command accept(Input inputContext);
}
