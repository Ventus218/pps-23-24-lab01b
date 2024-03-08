package e2.grid;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import e2.Pair;

public class GridImpl<T extends GridPlaceable> implements Grid<T> {

    private int sideSize;
    private final Map<Pair<Integer, Integer>, T> gridMap = new HashMap<>();

    public GridImpl(int sideSize) {
        if (sideSize < 1) {
            throw new IllegalArgumentException("Board size can't be smaller than 1");
        }
        this.sideSize = sideSize;
    }

    @Override
    public void add(T placeable) {
        int x = placeable.getX();
        int y = placeable.getY();
        checkBounds(x, y);
        var pair = new Pair<>(x, y);
        if (gridMap.containsKey(pair)) {
            throw new IllegalStateException("Trying to add to non-empty grid coordinates");
        }
        gridMap.put(pair, placeable);
    }

    @Override
    public Optional<T> get(int x, int y) {
        checkBounds(x, y);
        return Optional.ofNullable(gridMap.get(new Pair<>(x, y)));
    }

    @Override
    public Collection<T> getAll() {
        return gridMap.values();
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || y < 0 || x >= sideSize || y >= sideSize) {
            throw new IllegalStateException("Given coorindates exceed the grid bounds");
        }
    }

    @Override
    public int sideSize() {
        return sideSize;
    }
}
