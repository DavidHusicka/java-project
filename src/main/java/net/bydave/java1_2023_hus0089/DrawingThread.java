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

    final private GameState state;

    private final Canvas canvas;
    private final GraphicsContext gc;
    public DrawingThread(Canvas gameCanvas, GameState state) {
        this.canvas = gameCanvas;
        this.gc = canvas.getGraphicsContext2D();
        this.state = state;
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

}
