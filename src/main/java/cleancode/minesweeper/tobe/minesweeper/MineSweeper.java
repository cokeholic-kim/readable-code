package cleancode.minesweeper.tobe.minesweeper;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.game.GameInitialize;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutPutHandler;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

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

    private CellPosition getCellInputFromUser() {
        outPutHandler.showCommentForSelectingCell();
        CellPosition cellPositionFromUser = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPositionFromUser)) {
            throw new GameException("");
        }
        return cellPositionFromUser;
    }


    private UserAction getUserActionInputFromUser() {
        outPutHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
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

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;

    }
}
