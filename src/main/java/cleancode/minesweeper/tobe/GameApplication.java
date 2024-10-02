package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;

public class GameApplication {
    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper(new Advanced());
        mineSweeper.run();
    }
}
