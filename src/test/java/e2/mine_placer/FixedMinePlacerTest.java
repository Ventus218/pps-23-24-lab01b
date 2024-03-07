package e2.mine_placer;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import e2.Pair;

public class FixedMinePlacerTest extends MinePlacerTest {

    private static final int BOARD_SIZE = 3;
    Set<Pair<Integer, Integer>> minesPositions;

    FixedMinePlacerTest() {
        minesPositions = new HashSet<>();
        minesPositions.add(new Pair<>(0, 0));
        minesPositions.add(new Pair<>(0, 1));
        minesPositions.add(new Pair<>(0, 2));
        minesPositions.add(new Pair<>(1, 0));
        minesPositions.add(new Pair<>(2, 0));
    }

    @Override
    MinePlacer makeMinePlacer() {
        return new FixedMinePlacer(BOARD_SIZE, minesPositions);
    }

    @Test
    void throwsIfMinesExceedBoardBounds() {
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        mines.add(new Pair<>(0, BOARD_SIZE + 1));

        assertThrows(IllegalArgumentException.class, () -> new FixedMinePlacer(BOARD_SIZE, mines));
    }

    @Override
    @Test
    void throwsIfBoardSizeIsSmallerThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new FixedMinePlacer(0, minesPositions));
    }

    @Override
    @Test
    void throwsIfNumberOfMinesToPlaceIsSmallerThanZero() {
        // Collection sizes can't be smaller than zero¯
    }

    @Override
    @Test
    void throwsIfNumberOfMinesExceedBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedMinePlacer(2, minesPositions));
    }
}
