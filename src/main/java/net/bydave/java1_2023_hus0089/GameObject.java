package net.bydave.java1_2023_hus0089;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public interface GameObject {
    void draw(GraphicsContext gc);
    void update(long delta, GameState state);

    // x, y, -width, -height
    float[] getCollider();

    default void notifyKeyPressed(KeyEvent ev) {}
}
