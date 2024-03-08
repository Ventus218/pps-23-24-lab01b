package e2.cell;

import e2.Pair;

public class MineCell implements Cell {

    private final CellImpl cellImpl;

    public MineCell(int x, int y) {
        cellImpl = new CellImpl(x, y);
    }

    public MineCell(Pair<Integer, Integer> position) {
        this(position.getX(), position.getY());
    }

    public boolean wasHit() {
        return cellImpl.wasHit();
    }

    public void hit() {
        cellImpl.hit();
    }

    public boolean hasFlag() {
        return cellImpl.hasFlag();
    }

    public void setFlag(boolean flag) {
        cellImpl.setFlag(flag);
    }

    @Override
    public Integer getX() {
        return cellImpl.getX();
    }

    @Override
    public Integer getY() {
        return cellImpl.getY();
    }
}
