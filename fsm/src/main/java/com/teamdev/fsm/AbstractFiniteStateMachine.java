package com.teamdev.fsm;


import java.util.Set;

public abstract class AbstractFiniteStateMachine<

        Input extends InputContext,
        Output extends OutputContext,
        State extends Enum,
        Command extends StateTransitionCommand<Output>,
        Acceptor extends StateAcceptor<Input, Output, Command>,
        Matrix extends TransitionMatrix<State>,
        Error extends Exception,
        Result> {

    public Result run(Input inputContext, Output outputContext) throws Error{

        final Matrix matrix = getTransitionMatrix();

        State state = matrix.getStartState();

        while (state != matrix.getFinishState()) {

            state = moveForward(state, inputContext, outputContext);

            if (state == null) {
                deadlock(inputContext);
                break;
            }
        }

        return prepareResult(outputContext);
    }

    private State moveForward(State currentState, Input inputContext, Output outputContext) throws Error {

        final Set<State> possibleTransitions = getTransitionMatrix().
                getPossibleTransitions(currentState);


        for (State expectedState : possibleTransitions) {

            final Acceptor acceptor = getStateAcceptor(expectedState);

            final Command transitionCommand = acceptor.accept(inputContext);

            if (transitionCommand != null) {
                transitionCommand.execute(outputContext);
                return expectedState;
            }
        }

        return null;
    }

    protected abstract Result prepareResult(Output context);

    protected abstract void deadlock(Input context) throws Error;

    protected abstract Acceptor getStateAcceptor(State state);

    protected abstract Matrix getTransitionMatrix();
}
