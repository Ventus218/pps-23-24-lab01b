package e2.grid;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class GridPlaceableTest {
    protected GridPlaceable gridPlaceable;
    protected Grid grid;

    abstract int x();
    abstract int y();
    abstract GridPlaceable makeGridPlaceable();
    abstract int gridSideSize();
    abstract Grid makeGrid();

    @BeforeEach
    void init() {
        grid = makeGrid();
        gridPlaceable = makeGridPlaceable();
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
