package com.example.calculatorapp;

public class CalculatorController {
    private Calculator calculator;
    private StringBuilder currentInput;
    private CalculatorGUI gui;
    private boolean isFloatInput = false;

    public CalculatorController(Calculator calculator, CalculatorGUI gui) {
        this.calculator = calculator;
        this.currentInput = new StringBuilder();
        this.gui = gui;
        gui.setController(this);
    }

    public void handleDigit(String digit) {
        if (digit.equals(".")) {
            if (!isFloatInput) {
                currentInput.append(digit);
                isFloatInput = true;
            }
        } else {
            currentInput.append(digit);
        }
        gui.updateDisplay(currentInput.toString());
    }

    public void handleOperator(char operator) {
        calculator.setOperand1(parseInput());
        calculator.setOperator(operator);
        currentInput.setLength(0);
        isFloatInput = false;
    }

    public void handleEquals() {
        calculator.setOperand2(parseInput());
        calculator.calculate();

        if (calculator.isError()) {
            gui.updateDisplay("Error");
        } else {
            gui.updateDisplay(formatResult(calculator.getResult()));
        }
        currentInput.setLength(0);
        isFloatInput = false;
    }

    public void handleClear() {
        calculator.reset();
        currentInput.setLength(0);
        gui.updateDisplay("");
        isFloatInput = false;
    }

    // Parsing the current input to preserve the correct type
    private double parseInput() {
        return Double.parseDouble(currentInput.toString());
    }

    // Result formatting: integer or floating point
    private String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result); // integer
        } else {
            return String.valueOf(result); // float
        }
    }
}
