package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutPutHandler;

public class MineSweeper {
    private final int BOARD_ROW_SIZE = 8;
    private final int BOARD_COL_SIZE = 10;

    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    private final ConsoleOutPutHandler outPutHandler = new ConsoleOutPutHandler();
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public MineSweeper(GameLevel gameLevel){
        gameBoard = new GameBoard(gameLevel);
    }
    public void run() {
        outPutHandler.showGameStartComments();
        gameBoard.initializeGame();
        try {
            while (true) {
                outPutHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    outPutHandler.printWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    outPutHandler.printGameLoosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();

                actOnCell(cellInput, userActionInput);
            }
        } catch (GameException e) {
            outPutHandler.printExceptionMessage(e);
        } catch (Exception ignored) {
            outPutHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput,gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput,gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changeGameStatusDoLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }
        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    private void changeGameStatusDoLose() {
        gameStatus = -1;
    }


    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }



    private String getUserActionInputFromUser() {
        outPutHandler.printCommentForUserAction();
        return inputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        outPutHandler.printCommentForSelectingCell();
        return inputHandler.getUserInput();
    }

    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }





}
