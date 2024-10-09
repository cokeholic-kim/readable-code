package cleancode.minesweeper.tobe.cell;

public class NumberCell implements Cell {
    private final CellState cellState = CellState.initialize();
    private final int nearByLandMineCount;


    public NumberCell(int nearByLandMineCount) {
        this.nearByLandMineCount = nearByLandMineCount;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public CellSnapShot getSnapShot() {
        if(cellState.isOpened()){
            return CellSnapShot.ofNumber(nearByLandMineCount);
        }
        if(cellState.isFlagged()){
            return CellSnapShot.ofFlag();
        }
        return CellSnapShot.ofUnchecked();
    }

    @Override
    public void flag() {
        cellState.flag();
    }

    @Override
    public void open() {
        cellState.open();
    }

    @Override
    public boolean isChecked() {
        return cellState.isChecked();
    }

    @Override
    public boolean isOpened() {
        return cellState.isOpened();
    }
}
