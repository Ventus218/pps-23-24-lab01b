package e2.grid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridPlaceableImplTest {
    private static final int X = 1;
    private static final int Y = 1;
    private GridPlaceable gridPlaceable;
    private static final int GRID_SIDE_SIZE = 3;
    private Grid grid;

    @BeforeEach
    void init() {
        grid = new GridImpl(GRID_SIDE_SIZE);
        gridPlaceable = new GridPlaceableImpl(X, Y);
    }

    @Test
    void gridIsEmptyWhenCellNotAddedToAGrid() {
        assertTrue(gridPlaceable.grid().isEmpty());
    }

    @Test
    void gridIsSetWhenCellAddedToAGrid() {
        grid.add(gridPlaceable);
        assertTrue(gridPlaceable.grid().isPresent());
    }
}
