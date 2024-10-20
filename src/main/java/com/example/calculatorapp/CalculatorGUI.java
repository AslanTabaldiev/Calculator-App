package com.example.calculatorapp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Objects;

public class CalculatorGUI {
    private TextField display;
    private CalculatorController controller;

    public void setController(CalculatorController controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Text field to display
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 24px; -fx-alignment: center-right;");
        display.getStyleClass().add("text-field");
        root.getChildren().add(display);

        // Button grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        int row = 1;
        int col = 0;

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.getStyleClass().add("button");

            if (label.equals("/") || label.equals("*") || label.equals("-") || label.equals("+")) {
                button.getStyleClass().add("button-operator");
            } else if (label.equals("C")) {
                button.getStyleClass().add("button-clear");
            } else if (label.equals("=")) {
                button.getStyleClass().add("button-equals");
            }

            button.setPrefSize(60, 50);
            grid.add(button, col, row);

            button.setOnAction(e -> handleButtonClick(label));

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        root.getChildren().add(grid);

        Scene scene = new Scene(root, 300, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Calculator.css")).toExternalForm()); // подключаем CSS

        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator App");
        primaryStage.show();
    }

    public void handleButtonClick(String label) {
        switch (label) {
            case "C":
                controller.handleClear();
                break;
            case "=":
                controller.handleEquals();
                break;
            case "+": case "-": case "*": case "/":
                controller.handleOperator(label.charAt(0));
                break;
            default:
                controller.handleDigit(label);
                break;
        }
    }

    public void updateDisplay(String text) {
        display.setText(text);
    }
}
