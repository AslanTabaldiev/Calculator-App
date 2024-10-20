package com.example.calculatorapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Calculator calculator = new Calculator();
        CalculatorGUI gui = new CalculatorGUI();
        CalculatorController controller = new CalculatorController(calculator, gui);

        gui.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
