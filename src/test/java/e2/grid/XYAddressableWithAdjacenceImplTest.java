package e2.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XYAddressableWithAdjacenceImplTest {

    private static final int X = 1;
    private static final int Y = 1;
    private static final int GRID_SIDE_SIZE = 4;

    GridWithAdjacenceImpl<MockXYAddressableWithAdjacence> grid;
    MockXYAddressableWithAdjacence xyAddressable;

    @BeforeEach
    void init() {
        grid = new GridWithAdjacenceImpl<>(GRID_SIDE_SIZE);
        xyAddressable = new MockXYAddressableWithAdjacence(X, Y);
    }

    @Test
    void adjacenceIsEmptyWhenAddressableNotAdded() {
        assertEquals(0, xyAddressable.adjacentAddressables().size());
    }

    @Test
    void adjacenceIsEmptyWhenAddressableAddedButWithoutAdjacentAddressables() {
        grid.add(xyAddressable);
        assertEquals(0, xyAddressable.adjacentAddressables().size());
    }

    @Test
    void adjacenceIsNotEmptyWhenAddressableAddedWithAdjacentAddressables() {
        grid.add(new MockXYAddressableWithAdjacence(0, 0));
        grid.add(xyAddressable);
        assertEquals(1, xyAddressable.adjacentAddressables().size());
    }

    @Test
    void addressableIsAddedToOtherAddressablesWithAdjacenceList() {
        var other = new MockXYAddressableWithAdjacence(0, 0);
        grid.add(other);
        grid.add(xyAddressable);
        assertTrue(other.adjacentAddressables().contains(xyAddressable));
    }

    @Test
    void addressableIsNotAddedToOtherAddressablesWithAdjacenceListIfNotAdjacent() {
        var other = new MockXYAddressableWithAdjacence(3, 3);
        grid.add(other);
        grid.add(xyAddressable);
        assertAll(() -> assertFalse(other.adjacentAddressables().contains(xyAddressable)),
                () -> assertEquals(0, other.adjacentAddressables().size()));
    }

    public class MockXYAddressableWithAdjacence implements XYAddressableWithAdjacence<MockXYAddressableWithAdjacence> {

        private final XYAddressableWithAdjacenceImpl<MockXYAddressableWithAdjacence> xyAddressable;

        public MockXYAddressableWithAdjacence(int x, int y) {
            xyAddressable = new XYAddressableWithAdjacenceImpl<>(x, y);
        }

        public Integer getX() {
            return xyAddressable.getX();
        }

        public Integer getY() {
            return xyAddressable.getY();
        }

        public Collection<MockXYAddressableWithAdjacence> adjacentAddressables() {
            return xyAddressable.adjacentAddressables();
        }
    }
}
