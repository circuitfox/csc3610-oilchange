package edu.aurora.oilchange.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button mainButton;

    @FXML
    private void initialize() {
        mainButton.setOnAction(e -> System.exit(0));
    }
}
