package e2;

import java.util.Collection;
import java.util.Optional;

import e2.flaggable.Flaggable;
import e2.flaggable.FlaggableImpl;
import e2.grid.*;
import e2.hittable.Hittable;
import e2.hittable.HittableImpl;

public class MineSweeperCell extends GridPlaceableWithAdjacenceImpl implements Hittable, Flaggable {

    // private final GridPlaceableWithAdjacenceImpl gridPlaceable;
    private final Hittable hittable = new HittableImpl();
    private final Flaggable flaggable = new FlaggableImpl();
    private final boolean hasMine;

    public MineSweeperCell(int x, int y, boolean hasMine) {
        super(x, y);
        this.hasMine = hasMine;
        // gridPlaceable = new GridPlaceableWithAdjacenceImpl(x, y);
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

    public boolean hasMine() {
        return hasMine;
    }
}
