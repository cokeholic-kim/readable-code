package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.MineSweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutPutHandler;

public class GameApplication {
    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
                new Beginner(),
                new ConsoleInputHandler(),
                new ConsoleOutPutHandler()
        );
        MineSweeper game = new MineSweeper(gameConfig);
        game.initialize();
        game.run();
    }
}
