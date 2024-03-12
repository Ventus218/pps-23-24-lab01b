package e2.grid;

import java.util.Collection;

public interface XYAddressableWithAdjacence<AdjType extends XYAddressableWithAdjacence<AdjType>> extends XYAddressable {

    Collection<AdjType> adjacentAddressables();
}