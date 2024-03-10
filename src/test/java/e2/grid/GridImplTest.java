package e2.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridImplTest {

    private static final int SIDE_SIZE = 3;
    Grid gridImpl;

    @BeforeEach
    void init() {
        gridImpl = new GridImpl(SIDE_SIZE);
    }

    @Test
    void sideSizeCannotBeLowerThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new GridImpl(0));
    }

    @Test
    void sideSizeIsCorrect() {
        assertEquals(SIDE_SIZE, gridImpl.sideSize());
    }

    @Test
    void addPlaceable() {
        int x = 0;
        int y = 0;
        GridPlaceable placeable = new GridPlaceableImpl(x, y);
        gridImpl.add(placeable);
        assertEquals(Optional.of(placeable), gridImpl.get(x, y));
    }

    @Test
    void addPlaceableWithSameCoordinatesNotAllowed() {
        GridPlaceable placeable = new GridPlaceableImpl(0, 0);
        gridImpl.add(placeable);
        GridPlaceable placeable2 = new GridPlaceableImpl(0, 0);
        assertThrows(IllegalStateException.class, () -> gridImpl.add(placeable2));
    }

    @Test
    void addOutsideGridBoundsNotAllowed() {
        assertAll(
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new GridPlaceableImpl(-1, SIDE_SIZE))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new GridPlaceableImpl(SIDE_SIZE, -1))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new GridPlaceableImpl(0, SIDE_SIZE + 1))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new GridPlaceableImpl(SIDE_SIZE + 1, 0))));
    }

    @Test
    void getOutsideGridBoundsNotAllowed() {
        assertAll(
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.get(-1, SIDE_SIZE)),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.get(SIDE_SIZE, -1)),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.get(0, SIDE_SIZE + 1)),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.get(SIDE_SIZE + 1, 0)));
    }

    @Test
    void getAllIsCorrect() {
        List<GridPlaceable> placeables = new ArrayList<>();
        placeables.add(new GridPlaceableImpl(0, 0));
        placeables.add(new GridPlaceableImpl(0, 1));
        placeables.add(new GridPlaceableImpl(1, 0));

        for (var placeable : placeables) {
            gridImpl.add(placeable);
        }

        assertEquals(placeables.size(), gridImpl.getAll().size());
        assertAll(placeables.stream()
                .map(placeable -> () -> assertTrue(gridImpl.getAll().contains(placeable))));
    }

    @Test
    void correctlySetItselfToTheCell() {
        int x = 0;
        int y = 0;
        GridPlaceable placeable = new GridPlaceableImpl(x, y);
        gridImpl.add(placeable);
        assertEquals(Optional.of(gridImpl), placeable.grid());
    }
}
