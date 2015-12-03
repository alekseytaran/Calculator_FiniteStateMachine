package com.teamdev.calculatorImpl;

import com.teamdev.fsm.OutputContext;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack implements OutputContext {

    private Deque<Double> operandStack = new ArrayDeque<>();
    private Deque<BinaryOperator> operatorStack = new ArrayDeque<>();

    private Deque<Deque<Double>> listOperandStack = new ArrayDeque<>();
    private Deque<Deque<BinaryOperator>> listOperatorStack = new ArrayDeque<>();

    public EvaluationStack() {
        listOperandStack.push(operandStack);
        listOperatorStack.push(operatorStack);
    }

    public Deque<Deque<Double>> getListOperandStack() {
        return listOperandStack;
    }

    public Deque<Deque<BinaryOperator>> getListOperatorStack() {
        return listOperatorStack;
    }

    public Deque<Double> getOperandStack() {
        return operandStack;
    }

    public void popAllOperators() {
        while (!listOperatorStack.peek().isEmpty()) {

            popOperator();
        }
    }

    private void popOperator() {
        final Double rightOperand = listOperandStack.peek().pop();
        final Double leftOperand = listOperandStack.peek().pop();

        final BinaryOperator binaryOperator = listOperatorStack.peek().pop();

            final double result = binaryOperator.execute(leftOperand, rightOperand);


        listOperandStack.peek().push(result);
    }

    public void pushBinaryOperator(BinaryOperator operator) {

        while (!listOperatorStack.peek().isEmpty() && (listOperatorStack.peek().peek().compareTo(operator) > -1)) {

            popOperator();

        }

        listOperatorStack.peek().push(operator);
    }
}
