package net.bydave.java1_2023_hus0089;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class GameController {
    /*
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
     */

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label livesLabel;

    @FXML
    private Label bombsLabel;

    //private AnimationTimer animationTimer;
    private DrawingThread animationTimer;


    GameController() {
    }

    public void startGame() {
        this.animationTimer = new DrawingThread(gameCanvas);
        this.animationTimer.start();
    }

    public void notifyKeyPressed(KeyEvent ev) {
        this.animationTimer.notifyKeyPressed(ev);
    }
}