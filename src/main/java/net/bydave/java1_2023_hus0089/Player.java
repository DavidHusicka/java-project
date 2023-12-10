package net.bydave.java1_2023_hus0089;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Player implements GameObject {
    float x = (250 - 64) / (float)2;
    float y = 400;

    boolean isShiftDown = false;
    int tickCounter = 0;

    float sizeX = 64;
    float sizeY = 64;

    Image sprite;
    Player() {
        this.sprite = new Image(getClass().getResourceAsStream("Cat.gif"));
    }

    @Override
    public void update(long delta, GameState state) {
        float yDirection = 0;
        float xDirection = 0;
        this.isShiftDown = state.isShiftDown;
        if (state.isUpDown) {
            yDirection = -getMovementSpeed();
        }
        if (state.isDownDown) {
            yDirection = getMovementSpeed();
        }
        if (state.isRightDown) {
            xDirection = getMovementSpeed();
        }
        if (state.isLeftDown) {
            xDirection = -getMovementSpeed();
        }
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

        List<GameObject> collisionChecked = new LinkedList<>();
        collisionChecked.addAll(state.enemies);
        collisionChecked.addAll(state.enemyBullets);

        for (GameObject o : collisionChecked) {
            if (o.getColliderAsRectangle().intersects(this.getColliderAsRectangle())) {
                System.out.println("got hit");
            }
        }

        if (tickCounter % 8 == 0 && state.isFireDown) {
            state.playerBullets.add(new Bullet(x + this.sizeX / 2, y + 8, 0, -16));
        }
        tickCounter++;
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
        return new float[]{ x, y, 14, 14};
    }


    float getMovementSpeed() {
        if (isShiftDown) {
            return 2;
        }
        return 4;
    }
}
