package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public interface OutPutHandler {
    void showGameStartComments();

    void showBoard(GameBoard board);

    void showWinningComment();

    void showGameLoosingComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String s);
}
