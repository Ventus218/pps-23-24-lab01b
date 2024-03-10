package e2.grid;

import java.util.Collection;
import java.util.Optional;

public interface Grid {

    void add(GridPlaceable placeable);

    Optional<GridPlaceable> get(int x, int y);

    Collection<GridPlaceable> getAll();

    int sideSize();
}
