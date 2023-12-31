package net.bydave.java1_2023_hus0089;

import javafx.geometry.Rectangle2D;

public class Raccoon extends Enemy {
    Raccoon() {
        super(new RaccoonMovement(), "Raccoon.gif");
    }

    Raccoon(int tick) {
        super(new RaccoonMovement(tick), "Raccoon.gif");
        this.x = 190;
    }
    @Override
    public void update(long delta, GameState state) {
        super.update(delta, state);

        if (tickCounter % 180 == 0) {
            float[] playerPos = state.player.getCollider();
            float px = playerPos[0];
            float py = playerPos[1];
            px -= x;
            py -= y;
            float direction = px / py;
            state.enemyBullets.add(new Bullet(x + 16, y + 16, 5*direction, 5));
            state.enemyBullets.add(new Bullet(x + 0, y + 16, 5*direction, 5));
            state.enemyBullets.add(new Bullet(x + 32, y + 16, 5*direction, 5));
        }
    }
}
