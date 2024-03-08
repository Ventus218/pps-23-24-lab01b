package e2.cell;

import e2.Pair;
import e2.flaggable.Flaggable;
import e2.flaggable.FlaggableImpl;
import e2.hittable.Hittable;
import e2.hittable.HittableImpl;

public class CellImpl implements Cell {

    private final Pair<Integer, Integer> coordinates;
    private Hittable hittable = new HittableImpl();
    private Flaggable flaggable = new FlaggableImpl();

    public CellImpl(int x, int y) {
        this.coordinates = new Pair<>(x, y);
    }

    public CellImpl(Pair<Integer, Integer> coordinates) {
        this(coordinates.getX(), coordinates.getY());
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
        return coordinates.getX();
    }

    @Override
    public Integer getY() {
        return coordinates.getY();
    }
}
