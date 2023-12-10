package net.bydave.java1_2023_hus0089;

public interface GameStateChangedListener {
   void stateChanged(int score, int lives, int bombs);
   void gameEnded();

}
