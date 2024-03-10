package e2.grid;

import java.util.Collection;

public interface GridPlaceableWithAdjacence extends GridPlaceable {

    Collection<GridPlaceable> adjacentPlaceables();
}