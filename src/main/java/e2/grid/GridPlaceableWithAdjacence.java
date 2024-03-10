package e2.grid;

import java.util.Optional;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import e2.Pair;

public class GridPlaceableWithAdjacence implements GridPlaceable {

    private GridPlaceableImpl gridPlaceableImpl;
    List<GridPlaceable> adjacentPlaceables = new ArrayList<>();

    public GridPlaceableWithAdjacence(int x, int y) {
        gridPlaceableImpl = new GridPlaceableImpl(x, y);
    }

    public GridPlaceableWithAdjacence(Pair<Integer, Integer> position) {
        this(position.getX(), position.getY());
    }

    public Integer getX() {
        return gridPlaceableImpl.getX();
    }

    public Integer getY() {
        return gridPlaceableImpl.getY();
    }

    public Optional<Grid> grid() {
        return gridPlaceableImpl.grid();
    }

    public void setGrid(Grid grid) {
        gridPlaceableImpl.setGrid(grid);
        adjacentPlaceables.addAll(
                grid.getAll()
                        .stream()
                        .filter(placeable -> {
                            var xDelta = Math.abs(placeable.getX() - getX());
                            var yDelta = Math.abs(placeable.getY() - getY());
                            var delta = xDelta + yDelta;
                            return delta != 0 && xDelta <= 1 && yDelta <= 1;
                        })
                        .peek(placeable -> {
                            if (placeable instanceof GridPlaceableWithAdjacence p) {
                                p.adjacentPlaceables.add(this);
                            }
                        })
                        .toList());
    }

    public Collection<GridPlaceable> adjacentPlaceables() {
        return adjacentPlaceables;
    }
}
