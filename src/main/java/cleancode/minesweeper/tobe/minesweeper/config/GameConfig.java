package cleancode.minesweeper.tobe.minesweeper.config;

import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutPutHandler;

public class GameConfig {
    private final GameLevel gameLevel;
    private final InputHandler inputHandler;
    private final OutPutHandler outPutHandler;


    public GameConfig(GameLevel gameLevel, InputHandler inputHandler, OutPutHandler outPutHandler) {
        this.gameLevel = gameLevel;
        this.inputHandler = inputHandler;
        this.outPutHandler = outPutHandler;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutPutHandler getOutPutHandler() {
        return outPutHandler;
    }
}
