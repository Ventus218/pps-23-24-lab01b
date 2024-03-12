package e2.grid;

import java.util.Collection;
import java.util.Optional;

public class GridWithAdjacenceImpl<T extends XYAddressableWithAdjacence> implements Grid<T> {

    Grid<T> gridImpl;

    public GridWithAdjacenceImpl(int sideSize) {
        gridImpl = new GridImpl<>(sideSize);
    }

    public void add(T xyAddressable) {
        xyAddressable.adjacentAddressables().addAll(getAll()
                .stream()
                .filter(a -> {
                    var xDelta = Math.abs(a.getX() - xyAddressable.getX());
                    var yDelta = Math.abs(a.getY() - xyAddressable.getY());
                    var delta = xDelta + yDelta;
                    return delta != 0 && xDelta <= 1 && yDelta <= 1;
                })
                .peek(a -> a.adjacentAddressables().add(xyAddressable))
                .toList());
        gridImpl.add(xyAddressable);
    }

    public Optional<T> get(int x, int y) {
        return gridImpl.get(x, y);
    }

    public Collection<T> getAll() {
        return gridImpl.getAll();
    }

    public int sideSize() {
        return gridImpl.sideSize();
    }

}
