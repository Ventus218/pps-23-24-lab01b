package e2.cell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.Pair;

public abstract class CellTest {

    Cell cell;

    abstract Cell makeCell();

    abstract Pair<Integer, Integer> position();

    @BeforeEach
    void init() {
        cell = makeCell();
    }

    @Test
    void positionCorrectlyInitialized() {
        assertAll(() -> assertEquals(position().getX(), cell.getX()),
                () -> assertEquals(position().getY(), cell.getY()));
    }

}
