package net.bydave.java1_2023_hus0089;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet implements GameObject {
    float x;
    float y;
    float xDir = 0;
    float yDir = 0;

    Bullet(float x, float y, float xDir, float yDir) {
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(new Color(0.8,0.4,0,1));
        gc.fillRect(getCollider()[0], this.getCollider()[1], this.getCollider()[2], this.getCollider()[3]);
    }

    @Override
    public void update(long delta, GameState state) {
        x += xDir;
        y += yDir;

        if (y > 500) {
            state.playerBullets.remove(this);
            state.enemyBullets.remove(this);
        }
        if (y < -100) {
            state.playerBullets.remove(this);
            state.enemyBullets.remove(this);
        }
    }

    @Override
    public float[] getCollider() {
        return new float[]{x, y, 3, 7};
    }
}
