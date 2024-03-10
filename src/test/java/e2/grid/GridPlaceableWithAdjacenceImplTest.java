package e2.grid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GridPlaceableWithAdjacenceImplTest extends GridPlaceableTest {

    GridPlaceableWithAdjacenceImpl gridPlaceableWithAdjacence() {
        return (GridPlaceableWithAdjacenceImpl) gridPlaceable;
    }

    @Override
    int gridSideSize() {
        return 4;
    }

    @Override
    Grid makeGrid() {
        return new GridImpl(gridSideSize());
    }

    @Override
    GridPlaceable makeGridPlaceable() {
        return new GridPlaceableWithAdjacenceImpl(x(), y());
    }

    @Override
    int x() {
        return 1;
    }

    @Override
    int y() {
        return 1;
    }

    @Test
    void adjacenceIsEmptyWhenPlaceableNotAdded() {
        assertEquals(0, gridPlaceableWithAdjacence().adjacentPlaceables().size());
    }

    @Test
    void adjacenceIsEmptyWhenPlaceableAddedButWithoutAdjacentPlaceables() {
        grid.add(gridPlaceableWithAdjacence());
        assertEquals(0, gridPlaceableWithAdjacence().adjacentPlaceables().size());
    }

    @Test
    void adjacenceIsNotEmptyWhenPlaceableAddedWithAdjacentPlaceables() {
        grid.add(new GridPlaceableImpl(0, 0));
        grid.add(gridPlaceableWithAdjacence());
        assertEquals(1, gridPlaceableWithAdjacence().adjacentPlaceables().size());
    }

    @Test
    void placeableIsAddedToOtherPlaceablesWithAdjacenceList() {
        var other = new GridPlaceableWithAdjacenceImpl(0, 0);
        grid.add(other);
        grid.add(gridPlaceableWithAdjacence());
        assertTrue(other.adjacentPlaceables().contains(gridPlaceableWithAdjacence()));
    }

    @Test
    void placeableIsNotAddedToOtherPlaceablesWithAdjacenceListIfNotAdjacent() {
        var other = new GridPlaceableWithAdjacenceImpl(3, 3);
        grid.add(other);
        grid.add(gridPlaceableWithAdjacence());
        assertAll(() -> assertFalse(other.adjacentPlaceables().contains(gridPlaceableWithAdjacence())),
                () -> assertEquals(0, other.adjacentPlaceables().size()));
    }

}
