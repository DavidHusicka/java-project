package net.bydave.java1_2023_hus0089;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private final Stage stage;
    //private DrawingThread animationTimer;


    GameController(Stage stage) {
        this.stage = stage;
    }

    public void startGame() {
        this.state = new GameState(new GameStateChangedListenerImpl());
        this.animationTimer = new DrawingThread(gameCanvas, state);
        this.animationTimer.start();
    }

    @FXML
    void menuPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("menu-view.fxml"));
        try {
            fxmlLoader.setController(new MenuController(stage));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("want menu");
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
            try (PrintWriter pw = new PrintWriter(new FileWriter("scores.txt", true))) {
                pw.println(scoreLabel.getText());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}