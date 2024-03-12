package e2.grid;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import e2.Pair;

public class XYAddressableWithAdjacenceImpl<AdjType extends XYAddressableWithAdjacence<AdjType>>
        implements XYAddressableWithAdjacence<AdjType> {

    private XYAddressableImpl xyAddressableImpl;
    List<AdjType> adjacentAddressables = new ArrayList<>();

    public XYAddressableWithAdjacenceImpl(int x, int y) {
        xyAddressableImpl = new XYAddressableImpl(x, y);
    }

    public XYAddressableWithAdjacenceImpl(Pair<Integer, Integer> position) {
        this(position.getX(), position.getY());
    }

    public Integer getX() {
        return xyAddressableImpl.getX();
    }

    public Integer getY() {
        return xyAddressableImpl.getY();
    }

    @Override
    public Collection<AdjType> adjacentAddressables() {
        return adjacentAddressables;
    }
}
