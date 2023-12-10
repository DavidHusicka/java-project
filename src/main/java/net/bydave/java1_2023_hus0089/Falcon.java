package net.bydave.java1_2023_hus0089;

public class Falcon extends Enemy {
    Falcon() {
        super(new FalconMovement());
    }

    @Override
    public void update(long delta, GameState state) {
        super.update(delta, state);
        if (tickCounter % 25 == 0) {
            state.enemyBullets.add(new Bullet(x + 32, y + 32, 0, 1));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, 1, 1));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, 1, 0));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, 0, -1));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, -1, 0));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, -1, 1));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, 1, -1));
            state.enemyBullets.add(new Bullet(x + 32, y + 32, -1, -1));
        }
    }
}
