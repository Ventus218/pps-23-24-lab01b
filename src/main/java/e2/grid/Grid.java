package e2.grid;

import java.util.Collection;
import java.util.Optional;

public interface Grid {

    void add(XYAddressable xyAddressable);

    Optional<XYAddressable> get(int x, int y);

    Collection<XYAddressable> getAll();

    int sideSize();
}
