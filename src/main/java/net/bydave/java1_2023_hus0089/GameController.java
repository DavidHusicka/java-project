package net.bydave.java1_2023_hus0089;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}