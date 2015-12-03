package com.teamdev.calculatorImpl.operator;

public class Multiply extends AbstractBinaryOperator {

    public Multiply(Priority priority) {
        super(priority);
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
}
