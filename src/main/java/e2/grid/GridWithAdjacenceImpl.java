package e2.grid;

import java.util.Collection;
import java.util.Optional;

public class GridWithAdjacenceImpl implements Grid {

    Grid gridImpl;

    public GridWithAdjacenceImpl(int sideSize) {
        gridImpl = new GridImpl(sideSize);
    }

    public void add(XYAddressable xyAddressable) {
        if (xyAddressable instanceof XYAddressableWithAdjacence addressableWithAdjacence) {
            addressableWithAdjacence.adjacentAddressables().addAll(getAll()
                    .stream()
                    .filter(a -> {
                        var xDelta = Math.abs(a.getX() - xyAddressable.getX());
                        var yDelta = Math.abs(a.getY() - xyAddressable.getY());
                        var delta = xDelta + yDelta;
                        return delta != 0 && xDelta <= 1 && yDelta <= 1;
                    })
                    .peek(a -> {
                        if (a instanceof XYAddressableWithAdjacence aWithAdj) {
                            aWithAdj.adjacentAddressables().add(xyAddressable);
                        }
                    })
                    .toList());
        }
        gridImpl.add(xyAddressable);
    }

    public Optional<XYAddressable> get(int x, int y) {
        return gridImpl.get(x, y);
    }

    public Collection<XYAddressable> getAll() {
        return gridImpl.getAll();
    }

    public int sideSize() {
        return gridImpl.sideSize();
    }

}
