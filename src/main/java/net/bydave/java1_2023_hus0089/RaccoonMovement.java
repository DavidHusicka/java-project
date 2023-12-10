package net.bydave.java1_2023_hus0089;

public class RaccoonMovement implements MovementFunction{
    int tickCounter = 0;
    @Override
    public float[] move() {
        tickCounter++;
        return switch ((tickCounter/90) % 4) {
            case 0 -> new float[]{0, 0.1f};
            case 1 -> new float[]{2, 0};
            case 2 -> new float[]{0, 0};
            case 3 -> new float[]{-2, 0};
            default -> new float[]{0, 0};
        };
    }
}
