package net.bydave.java1_2023_hus0089;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public interface GameObject {
    void draw(GraphicsContext gc);
    void update(long delta, GameState state);

    // x, y, -width, -height
    float[] getCollider();
    default Rectangle2D getColliderAsRectangle() {
        float[] col = this.getCollider();
        return new Rectangle2D(col[0], col[1], col[2], col[3]);
    }
}
