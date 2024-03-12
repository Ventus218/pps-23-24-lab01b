package e2.grid;

import java.util.Collection;
import java.util.Optional;

public interface Grid<T extends XYAddressable> {

    void add(T xyAddressable);

    Optional<T> get(int x, int y);

    Collection<T> getAll();

    int sideSize();
}
