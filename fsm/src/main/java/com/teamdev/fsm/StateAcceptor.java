package com.teamdev.fsm;


public interface StateAcceptor<

        Input extends InputContext,
        Output extends OutputContext,
        Command extends StateTransitionCommand<Output>,
        Error extends Exception> {

    Command accept(Input inputContext) throws Error;
}
