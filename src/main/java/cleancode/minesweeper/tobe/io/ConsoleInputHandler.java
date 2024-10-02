package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.BoardIndexConverter;
import cleancode.minesweeper.tobe.position.CellPosition;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    public final Scanner SCANNER = new Scanner(System.in);
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    @Override
    public String getUserInput() {
        return SCANNER.nextLine();
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(userInput);
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(userInput);
        return CellPosition.of(selectedRowIndex,selectedColIndex);
    }
}
