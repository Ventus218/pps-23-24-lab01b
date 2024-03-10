package e2.grid;

import java.util.Collection;

public interface XYAddressableWithAdjacence extends XYAddressable {

    Collection<XYAddressable> adjacentAddressables();
}