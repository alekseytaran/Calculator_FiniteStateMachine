package com.teamdev.calculatorImpl;

import com.teamdev.fsm.InputContext;

public class MathExpressionReader implements InputContext {

    final private String expression;

    private int position = 0;

    public MathExpressionReader(String expression) {
        this.expression = expression;
    }

    public void skipSpaces() {
        while (hasMoreElements() && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    public boolean hasMoreElements() {
        return position < expression.length();
    }

    public int getPosition() {
        return position;
    }

    public void movePosition(int value) {
        position += value;
    }

    public String getRemainingExpression() {

        skipSpaces();

        return expression.substring(position);
    }

}
