package com.teamdev.calculatorImpl;

public interface BinaryOperator extends Comparable<BinaryOperator> {
    double execute(double leftOperand, double rightOperand);
}
