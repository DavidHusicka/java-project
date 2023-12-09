package net.bydave.java1_2023_hus0089;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Player implements GameObject {
    float x = (250 - 64) / (float)2;
    float y = 400;

    float yDirection = 0;
    float xDirection = 0;
    boolean isShiftDown = false;

    float sizeX = 64;
    float sizeY = 64;

    Image sprite;
    Player() {
        this.sprite = new Image(getClass().getResourceAsStream("Cat.gif"));
    }

    @Override
    public void update(long delta, GameState state) {
        this.x += xDirection;
        this.y += yDirection;

        if (this.x < -sizeX / 2) {
            this.x = -sizeX / 2;
        }
        if (this.x > state.sceneSizeX - sizeX / 2) {
            this.x = state.sceneSizeX - sizeX / 2;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y > state.sceneSizeY - sizeY) {
            this.y = state.sceneSizeY - sizeY;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.sprite, x, y, sizeX, sizeY);
        if (isShiftDown) {
            float[] collider = this.getCollider();
            gc.setFill(new Color(1, 0, 0, 1));
            gc.fillOval(collider[0], collider[1], 14, 14);
        }
    }

    @Override
    public float[] getCollider() {
        float x = this.x + 64 / (float)2 - 7;
        float y = this.y + 64 / (float)2 - 2;
        return new float[]{ x, y, x + 14, y + 14};
    }

    @Override
    public void notifyKeyPressed(KeyEvent ev) {
        KeyCode code = ev.getCode();
        if (ev.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (code) {
                case RIGHT:
                    xDirection = getMovementSpeed();
                    break;
                case LEFT:
                    xDirection = -getMovementSpeed();
                    break;
                case UP:
                    yDirection = -getMovementSpeed();
                    break;
                case DOWN:
                    yDirection = getMovementSpeed();
                    break;
            }
        }
        else {
            switch (code) {
                case RIGHT:
                case LEFT:
                    xDirection = 0;
                    break;
                case UP:
                case DOWN:
                    yDirection = 0;
                    break;
            }
        }
        this.isShiftDown = ev.isShiftDown();
    }

    float getMovementSpeed() {
        if (isShiftDown) {
            return 2;
        }
        return 4;
    }
}
