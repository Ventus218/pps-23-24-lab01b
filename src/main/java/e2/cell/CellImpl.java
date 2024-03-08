package e2.cell;

import e2.Pair;

public class CellImpl implements Cell {

    private final Pair<Integer, Integer> coordinates;

    public CellImpl(int x, int y) {
        this.coordinates = new Pair<>(x, y);
    }

    public CellImpl(Pair<Integer, Integer> coordinates) {
        this(coordinates.getX(), coordinates.getY());
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
