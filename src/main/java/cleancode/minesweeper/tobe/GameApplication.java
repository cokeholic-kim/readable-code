package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutPutHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutPutHandler;

public class GameApplication {
    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        OutPutHandler outPutHandler = new ConsoleOutPutHandler();
        MineSweeper game = new MineSweeper(new Beginner(), inputHandler, outPutHandler);
        game.initialize();
        game.run();
    }
}
