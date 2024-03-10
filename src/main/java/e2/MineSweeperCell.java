package e2;

import java.util.Collection;

import e2.flaggable.Flaggable;
import e2.flaggable.FlaggableImpl;
import e2.grid.*;
import e2.hittable.Hittable;
import e2.hittable.HittableImpl;

public class MineSweeperCell implements XYAddressableWithAdjacence, Hittable, Flaggable {

    private final XYAddressableWithAdjacence xyAddressable;
    private final Hittable hittable = new HittableImpl();
    private final Flaggable flaggable = new FlaggableImpl();
    private final boolean hasMine;

    public MineSweeperCell(int x, int y, boolean hasMine) {
        this.hasMine = hasMine;
        xyAddressable = new XYAddressableWithAdjacenceImpl(x, y);
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

    public Integer getX() {
        return xyAddressable.getX();
    }

    public Integer getY() {
        return xyAddressable.getY();
    }

    public Collection<XYAddressable> adjacentAddressables() {
        return xyAddressable.adjacentAddressables();
    }

    public boolean hasMine() {
        return hasMine;
    }
}
