package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapShot;

public interface CellSignProvidable {
    boolean supports(CellSnapShot cellSnapShot);
    String provide(CellSnapShot cellSnapShot);

}
