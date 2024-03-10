package e2.grid;

import java.util.Optional;

public interface GridPlaceable extends XYAddressable {

    void setGrid(Grid grid);

    Optional<Grid> grid();
}
