package net.bydave.java1_2023_hus0089;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Enemy implements GameObject {
    float x = 0;
    float y = 0;
    float sizeX = 64;
    float sizeY = 64;

    int tickCounter = 0;
    Image sprite;
    MovementFunction movePattern;
    Enemy(MovementFunction movePattern){
        this.movePattern = movePattern;
        this.sprite = new Image(getClass().getResourceAsStream("Falcon.gif"));
    }

    @Override
    public void update(long delta, GameState state) {
        float[] movement = this.movePattern.move();
        this.x += movement[0];
        this.y += movement[1];

        for (GameObject o : state.playerBullets) {
            if (o.getColliderAsRectangle().intersects(this.getColliderAsRectangle())) {
                state.enemies.remove(this);
            }
        }
        tickCounter++;
    }

    @Override
    public float[] getCollider() {
        return new float[]{x + 16, y + 16, 64 - 32, 64 - 32};
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(sprite, x, y, 64, 64);
        //gc.setFill(new Color(0, 1, 0, 1));
        //gc.fillRect(getCollider()[0], getCollider()[1],  getCollider()[2], getCollider()[3]);
    }

}
