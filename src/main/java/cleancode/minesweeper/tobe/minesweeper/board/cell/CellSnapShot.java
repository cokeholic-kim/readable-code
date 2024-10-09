package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapShot {
    private final CellSnapshotStatus status;
    private final int nearByLandMineCount;

    private CellSnapShot(CellSnapshotStatus status, int nearByLandMineCount) {
        this.status = status;
        this.nearByLandMineCount = nearByLandMineCount;
    }

    public static CellSnapShot of(CellSnapshotStatus status, int nearByLandMineCount){
        return new CellSnapShot(status,nearByLandMineCount);
    }

    public static CellSnapShot ofEmpty(){
        return new CellSnapShot(CellSnapshotStatus.EMPTY,0);
    }

    public static CellSnapShot ofFlag(){
        return new CellSnapShot(CellSnapshotStatus.FLAG,0);
    }

    public static CellSnapShot ofLandMine(){
        return new CellSnapShot(CellSnapshotStatus.LAND_MINE,0);
    }

    public static CellSnapShot ofNumber(int nearByLandMineCount){
        return new CellSnapShot(CellSnapshotStatus.NUMBER,nearByLandMineCount);
    }

    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return status == cellSnapshotStatus;
    }

    public static CellSnapShot ofUnchecked(){
        return new CellSnapShot(CellSnapshotStatus.UNCHECKED,0);
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearByLandMineCount() {
        return nearByLandMineCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CellSnapShot snapShot = (CellSnapShot) o;
        return nearByLandMineCount == snapShot.nearByLandMineCount && status == snapShot.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearByLandMineCount);
    }


}
