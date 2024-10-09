package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.config.GameConfig;
import cleancode.minesweeper.tobe.game.GameInitialize;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutPutHandler;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

public class MineSweeper implements GameRunnable, GameInitialize {
    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutPutHandler outPutHandler;

    public MineSweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outPutHandler = gameConfig.getOutPutHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        outPutHandler.showGameStartComments();
        try {
            while (gameBoard.isInProgress()) {
                outPutHandler.showBoard(gameBoard);
                CellPosition cellPosition = getCellInputFromUser();
                UserAction userActionInput = getUserActionInputFromUser();

                actOnCell(cellPosition, userActionInput);
            }
        } catch (GameException e) {
            outPutHandler.showExceptionMessage(e);
        } catch (Exception ignored) {
            outPutHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
        }
        outPutHandler.showBoard(gameBoard);
        if (gameBoard.isWinStatus()) {
            outPutHandler.showWinningComment();
        }
        if (gameBoard.isLoseStatus()) {
            outPutHandler.showGameLoosingComment();
        }
    }

    private void actOnCell(CellPosition cellPosition, UserAction userActionInput) {
        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flagAt(cellPosition);
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            gameBoard.openAt(cellPosition);
            return;
        }
        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;

    }

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }


    private UserAction getUserActionInputFromUser() {
        outPutHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private CellPosition getCellInputFromUser() {
        outPutHandler.showCommentForSelectingCell();
        CellPosition cellPositionFromUser = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPositionFromUser)) {
            throw new GameException("");
        }
        return cellPositionFromUser;
    }
}
