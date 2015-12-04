package com.teamdev.calculatorImpl;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private Deque<Double> operandStack = new ArrayDeque<>();
    private Deque<BinaryOperator> operatorStack = new ArrayDeque<>();

    private final EvaluationStack parent;
    private final EvaluationContextCloser closer;

    public EvaluationStack() {
        this(null, null);
    }

    public EvaluationStack(EvaluationStack parent, EvaluationContextCloser closer) {
        this.parent = parent;
        this.closer = closer;
    }

    public Deque<Double> getOperandStack() {
        return operandStack;
    }

    public Deque<BinaryOperator> getOperatorStack() {
        return operatorStack;
    }

    public EvaluationStack getParent() {
        return parent;
    }

    public EvaluationContextCloser getCloser() {
        return closer;
    }

    public void popAllOperators() {
        while (!operatorStack.isEmpty()) {

            popOperator();
        }
    }

    private void popOperator() {

        final Double rightOperand = operandStack.pop();
        final Double leftOperand = operandStack.pop();

        final BinaryOperator binaryOperator = operatorStack.pop();

        final double result = binaryOperator.execute(leftOperand, rightOperand);

        operandStack.push(result);
    }

    public void pushBinaryOperator(BinaryOperator operator) {

        while (!operatorStack.isEmpty() &&
                (operatorStack.peek().compareTo(operator) > -1)) {

            popOperator();

        }

        operatorStack.push(operator);
    }

}
