package net.bydave.java1_2023_hus0089;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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

    GameState state = new GameState(new GameStateChangedListenerImpl());

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label livesLabel;

    @FXML
    private Label bombsLabel;

    private AnimationTimer animationTimer;
    //private DrawingThread animationTimer;


    GameController() {
    }

    public void startGame() {
        this.state = new GameState(new GameStateChangedListenerImpl());
        this.animationTimer = new DrawingThread(gameCanvas, state);
        this.animationTimer.start();
    }

    public void notifyKeyPressed(KeyEvent ev) {
        //this.state.player.notifyKeyPressed(ev);
        KeyCode code = ev.getCode();
        if (ev.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (code) {
                case RIGHT:
                    state.isRightDown = true;
                    break;
                case LEFT:
                    state.isLeftDown = true;
                    break;
                case UP:
                    state.isUpDown = true;
                    break;
                case DOWN:
                    state.isDownDown = true;
                    break;
                case SHIFT:
                    state.isShiftDown = true;
                    break;
                case Z:
                    state.isFireDown = true;
                    break;
                case X:
                    state.isBombDown = true;
                    break;
            }
        }
        else {
            switch (code) {
                case RIGHT:
                    state.isRightDown = false;
                    break;
                case LEFT:
                    state.isLeftDown = false;
                    break;
                case UP:
                    state.isUpDown = false;
                    break;
                case DOWN:
                    state.isDownDown = false;
                    break;
                case SHIFT:
                    state.isShiftDown = false;
                    break;
                case Z:
                    state.isFireDown = false;
                    break;
                case X:
                    state.isBombDown = false;
                    break;
            }
        }
    }

    public class GameStateChangedListenerImpl implements GameStateChangedListener {
        @Override
        public void stateChanged(int score, int lives, int bombs) {
            scoreLabel.setText(""+ score);
            livesLabel.setText(""+ lives);
            bombsLabel.setText(""+bombs);
        }

        @Override
        public void gameEnded() {

        }
    }
}