package net.bydave.java1_2023_hus0089;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class GameState {
    // these are separate lists because like this, it has a O(n) complexity to check hits
    // putting everything into the same list would require checking every object against every object making it O(n*n)
    public List<GameObject> enemies = new Vector<>();
    public List<GameObject> enemyBullets = new Vector<>();
    public List<GameObject> playerBullets = new Vector<>();
    public GameObject player = new Player();
    public float sceneSizeX = 250;
    public float sceneSizeY = 400;

    public boolean isRightDown = false;
    public boolean isLeftDown = false;
    public boolean isDownDown = false;
    public boolean isUpDown = false;
    public boolean isShiftDown = false;
    public boolean isFireDown = false;
    public boolean isBombDown = false;

    private List<GameObject> objects = new LinkedList<>();

    GameState() {
        enemies.add(new Falcon());
    }

    void update(long delta) {
        // is it possible to do the same thing with streams?
        // it's possible with iterators in rust for sure
        objects.clear();
        objects.add(player);
        objects.addAll(enemies);
        objects.addAll(enemyBullets);
        objects.addAll(playerBullets);

        for (GameObject o : objects) {
            o.update(delta, this);
        }
    }

    // requires update first before calling draw
    void draw(GraphicsContext gc) {
        for (GameObject o : objects) {
            o.draw(gc);
        }

    }

}
