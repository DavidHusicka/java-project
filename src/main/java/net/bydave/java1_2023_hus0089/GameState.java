package net.bydave.java1_2023_hus0089;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
    private int score = 0;
    private int bombs = 5;
    private int lives = 3;

    int tickCounter = 1;
    int lastBomb = -1000;

    private final List<GameObject> objects = new LinkedList<>();

    private final GameStateChangedListener listener;
    private final Random random;

    GameState(GameStateChangedListener listener) {
        this.enemies.add(new Raccoon());
        this.listener = listener;
        this.random = new Random();
    }

    public int getScore() {
       return score;
    }

    public int getLives() {
        return lives;
    }

    public int getBombs() {
        return bombs;
    }

    public void setScore(int s) {
        this.score = s;
        this.listener.stateChanged(score, lives, bombs);
    }

    public void setLives(int s) {
        this.lives=s;
        this.listener.stateChanged(score, lives, bombs);
    }

    public void setBombs(int s) {
        this.bombs = s;
        this.listener.stateChanged(score, lives, bombs);
    }

    void update(long delta) {
        if (lives <= 0) {
            return;
        }
        if (tickCounter % (250 - tickCounter / 600) == 0) {
            switch (random.nextInt(4) % 4) {
                case 0:
                    enemies.add(new Falcon());
                    break;
                case 1:
                    enemies.add(new Raccoon());
                    break;
                case 2:
                    enemies.add(new Falcon(25*3));
                    break;
                case 3:
                    enemies.add(new Raccoon(180));
                    break;
            }
            //enemies.add(new Falcon());
        }

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

        if (isBombDown && tickCounter - lastBomb > 60 && bombs > 0) {
            this.setBombs(bombs - 1);
            this.isBombDown = false;
            this.enemies.clear();
            this.enemyBullets.clear();
            this.lastBomb = tickCounter;
        }

        if (this.lives <= 0) {
            this.listener.gameEnded();
        }
        tickCounter++;
    }

    // requires update first before calling draw
    void draw(GraphicsContext gc) {
        if (this.tickCounter - lastBomb < 15 && lives > 0) {
            gc.setFill(new Color(0,1,1,1));
            gc.fillRect(0,0,sceneSizeX, sceneSizeY);
        }
        for (GameObject o : objects) {
            o.draw(gc);
        }

    }

}
