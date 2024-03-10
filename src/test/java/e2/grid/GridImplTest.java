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
    void addAddressable() {
        int x = 0;
        int y = 0;
        XYAddressable addressable = new XYAddressableImpl(x, y);
        gridImpl.add(addressable);
        assertEquals(Optional.of(addressable), gridImpl.get(x, y));
    }

    @Test
    void addAddressableWithSameCoordinatesNotAllowed() {
        XYAddressable addressable = new XYAddressableImpl(0, 0);
        gridImpl.add(addressable);
        XYAddressable addressable2 = new XYAddressableImpl(0, 0);
        assertThrows(IllegalStateException.class, () -> gridImpl.add(addressable2));
    }

    @Test
    void addOutsideGridBoundsNotAllowed() {
        assertAll(
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new XYAddressableImpl(-1, SIDE_SIZE))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new XYAddressableImpl(SIDE_SIZE, -1))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new XYAddressableImpl(0, SIDE_SIZE + 1))),
                () -> assertThrows(IllegalStateException.class,
                        () -> gridImpl.add(new XYAddressableImpl(SIDE_SIZE + 1, 0))));
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
        List<XYAddressable> addressables = new ArrayList<>();
        addressables.add(new XYAddressableImpl(0, 0));
        addressables.add(new XYAddressableImpl(0, 1));
        addressables.add(new XYAddressableImpl(1, 0));

        for (var addressable : addressables) {
            gridImpl.add(addressable);
        }

        assertEquals(addressables.size(), gridImpl.getAll().size());
        assertAll(addressables.stream()
                .map(addressable -> () -> assertTrue(gridImpl.getAll().contains(addressable))));
    }
}
