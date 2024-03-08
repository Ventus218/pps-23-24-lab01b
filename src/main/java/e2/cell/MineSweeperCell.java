package e2.cell;

import e2.Pair;
import e2.flaggable.Flaggable;
import e2.flaggable.FlaggableImpl;
import e2.hittable.Hittable;
import e2.hittable.HittableImpl;

public class MineSweeperCell implements Cell, Hittable, Flaggable {

    private final CellImpl cellImpl;
    private final Hittable hittable = new HittableImpl();
    private final Flaggable flaggable = new FlaggableImpl();
    private final boolean hasMine;

    public MineSweeperCell(int x, int y, boolean hasMine) {
        this.hasMine = hasMine;
        cellImpl = new CellImpl(x, y);
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
        return cellImpl.getX();
    }

    @Override
    public Integer getY() {
        return cellImpl.getY();
    }

    public boolean hasMine() {
        return hasMine;
    }
}
