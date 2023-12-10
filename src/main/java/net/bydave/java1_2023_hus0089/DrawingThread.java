package net.bydave.java1_2023_hus0089;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Vector;

public class DrawingThread extends AnimationTimer {

    private final GameState state = new GameState();

    private final Canvas canvas;
    private final GraphicsContext gc;
    public DrawingThread(Canvas gameCanvas) {
        this.canvas = gameCanvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void handle(long l) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(new Color(1,1,1, 1));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /*
        gc.setFill(new Color(0, 0, 0, 1));
        gc.fillRect(10, 10, 50, 50);
         */

        state.update(l);
        state.draw(gc);
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

}
