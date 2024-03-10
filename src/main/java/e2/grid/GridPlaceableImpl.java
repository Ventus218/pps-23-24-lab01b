package e2.grid;

import java.util.Optional;

import e2.Pair;

public class GridPlaceableImpl implements GridPlaceable {

    private final XYAddressable xyAddressableImpl;
    private Optional<Grid> grid;

    public GridPlaceableImpl(int x, int y) {
        xyAddressableImpl = new XYAddressableImpl(x, y);
        grid = Optional.empty();
    }

    public GridPlaceableImpl(Pair<Integer, Integer> position) {
        this(position.getX(), position.getY());
    }

    public Integer getX() {
        return xyAddressableImpl.getX();
    }

    public Integer getY() {
        return xyAddressableImpl.getY();
    }

    @Override
    public Optional<Grid> grid() {
        return grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = Optional.of(grid);
    }
}
