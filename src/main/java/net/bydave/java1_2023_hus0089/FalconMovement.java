package net.bydave.java1_2023_hus0089;

public class FalconMovement implements MovementFunction {
    int tickCounter = 0;
    @Override
    public float[] move() {
        tickCounter++;
        return switch ((tickCounter/25) % 4) {
            case 0 -> new float[]{0, 8.5f};
            case 1 -> new float[]{8, 0};
            case 2 -> new float[]{0, -8};
            case 3 -> new float[]{-8, 0};
            default -> new float[]{0, 0};
        };
    }

    FalconMovement() {}
    FalconMovement(int tickCounter) {
        this.tickCounter = tickCounter;
    }
}
