package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;

public class GameApplication {
    public static void main(String[] args) {
        MineSweeper game = new MineSweeper(new Advanced());
        game.initialize();
        game.run();
    }
}
