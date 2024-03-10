package e2;

import java.util.Optional;

import e2.flaggable.Flaggable;
import e2.flaggable.FlaggableImpl;
import e2.grid.Grid;
import e2.grid.GridPlaceable;
import e2.grid.GridPlaceableImpl;
import e2.hittable.Hittable;
import e2.hittable.HittableImpl;

public class MineSweeperCell implements GridPlaceable, Hittable, Flaggable {

    private final GridPlaceable gridPlaceable;
    private final Hittable hittable = new HittableImpl();
    private final Flaggable flaggable = new FlaggableImpl();
    private final boolean hasMine;

    public MineSweeperCell(int x, int y, boolean hasMine) {
        this.hasMine = hasMine;
        gridPlaceable = new GridPlaceableImpl(x, y);
    }

    public MineSweeperCell(Pair<Integer, Integer> position, boolean hasMine) {
        this(position.getX(), position.getY(), hasMine);
    }

    public boolean wasHit() {
        return hittable.wasHit();
    }

    public void hit() {
        hittable.hit();
    }

    public boolean hasFlag() {
        return flaggable.hasFlag();
    }

    public void setFlag(boolean b) {
        flaggable.setFlag(b);
    }

    @Override
    public Integer getX() {
        return gridPlaceable.getX();
    }

    @Override
    public Integer getY() {
        return gridPlaceable.getY();
    }

    public boolean hasMine() {
        return hasMine;
    }

    @Override
    public void setGrid(Grid grid) {
        gridPlaceable.setGrid(grid);
    }

    @Override
    public Optional<Grid> grid() {
        return gridPlaceable.grid();
    }
}
