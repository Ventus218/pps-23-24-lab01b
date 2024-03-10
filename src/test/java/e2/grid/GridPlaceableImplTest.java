package e2.grid;

public class GridPlaceableImplTest extends GridPlaceableTest {
    @Override
    int gridSideSize() {
        return 3;
    }

    @Override
    Grid makeGrid() {
        return new GridImpl(gridSideSize());
    }

    @Override
    GridPlaceable makeGridPlaceable() {
        return new GridPlaceableImpl(x(), y());
    }

    @Override
    int x() {
        return 1;
    }

    @Override
    int y() {
        return 1;
    }
}
