package e2.grid;

import java.util.Collection;
import java.util.Optional;

public interface Grid<T extends GridPlaceable> {

    void add(T placeable);

    Optional<T> get(int x, int y);

    Collection<T> getAll();

    int sideSize();
}
