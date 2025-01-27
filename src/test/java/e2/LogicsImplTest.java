package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.mine_placer.FixedMinePlacer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class LogicsImplTest {
    private Logics logicsImpl;
    private static final int BOARD_SIZE = 7;
    private static final int NUMBER_OF_MINES = 7;
    private static final int CALLS_TO_TEST_RANDOMICITY = 20;

    @BeforeEach
    void init() {
        logicsImpl = new LogicsImpl(BOARD_SIZE, NUMBER_OF_MINES);
    }

    @Test
    void minesCannotCoverTheBoardCompletely() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(BOARD_SIZE, BOARD_SIZE * BOARD_SIZE));
    }

    @Test
    void numberOfPlacedMinesIsCorrect() {
        assertEquals(NUMBER_OF_MINES, logicsImpl.mines().size());
    }

    @Test
    void minesArePlacedRandomly() {
        Set<Pair<Integer, Integer>> mines = logicsImpl.mines();
        for (int i = 0; i < CALLS_TO_TEST_RANDOMICITY; i++) {
            init();
            if (!mines.equals(logicsImpl.mines())) {
                return;
            }
        }
        fail(CALLS_TO_TEST_RANDOMICITY + " calls returned always the same mine configuration.");
    }

    @Test
    void getRightCell() {
        MineSweeperCell cell = logicsImpl.getCell(new Pair<>(0, 0));
        assertEquals(0, cell.getX());
        assertEquals(0, cell.getY());
        assertFalse(cell.wasHit());
        assertFalse(cell.hasFlag());
    }

    @Test
    void hitReturnsTrueWhenHittingAMine() {
        assertAll(logicsImpl.mines()
                .stream()
                .map(mine -> () -> assertTrue(logicsImpl.hit(mine))));
    }

    @Test
    void hitReturnsFalseWhenNotHittingAMine() {
        Set<Pair<Integer, Integer>> positionsWithoutMines = new HashSet<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Pair<Integer, Integer> position = new Pair<>(row, column);
                if (!logicsImpl.mines().contains(position)) {
                    positionsWithoutMines.add(position);
                }
            }
        }
        assertAll(positionsWithoutMines
                .stream()
                .map(positionWithoutMine -> {
                    return () -> {
                        if (!logicsImpl.getCell(positionWithoutMine).wasHit()) {
                            assertFalse(logicsImpl.hit(positionWithoutMine));
                        }
                    };
                }));
    }

    @Test
    void hitThrowsIfCellAlreadyHit() {
        var position = new Pair<Integer, Integer>(0, 0);
        logicsImpl.hit(position);
        assertThrows(IllegalStateException.class, () -> logicsImpl.hit(position));
    }

    @Test
    void numberOfAdjacentMines() {
        Set<Pair<Integer, Integer>> minesPositions = new HashSet<>();
        minesPositions.add(new Pair<>(0, 0));
        minesPositions.add(new Pair<>(0, 1));
        minesPositions.add(new Pair<>(0, 2));
        minesPositions.add(new Pair<>(1, 0));
        minesPositions.add(new Pair<>(2, 0));

        // - 0 1 2
        // 0|*|*|*|
        // 1|*|5|2|
        // 2|*|2|0|
        logicsImpl = new LogicsImpl(new FixedMinePlacer(3, minesPositions));

        assertAll(
                () -> assertEquals(5, logicsImpl.numberOfAdjacentMines(new Pair<>(1, 1))),
                () -> assertEquals(2, logicsImpl.numberOfAdjacentMines(new Pair<>(1, 2))),
                () -> assertEquals(2, logicsImpl.numberOfAdjacentMines(new Pair<>(2, 1))),
                () -> assertEquals(0, logicsImpl.numberOfAdjacentMines(new Pair<>(2, 2))));
    }

    @Test
    void numberOfAdjacentMinesThrowsIfCalledOnAMine() {
        Set<Pair<Integer, Integer>> minesPositions = new HashSet<>();
        minesPositions.add(new Pair<>(0, 0));

        logicsImpl = new LogicsImpl(new FixedMinePlacer(3, minesPositions));

        assertThrows(IllegalStateException.class, () -> logicsImpl.numberOfAdjacentMines(new Pair<>(0, 0)));
    }

    @Test
    void hitIsCalledAutomaticallyOnCellsWithoutAdjacentMines() {
        Set<Pair<Integer, Integer>> minesPositions = new HashSet<>();
        minesPositions.add(new Pair<>(0, 0));
        minesPositions.add(new Pair<>(0, 1));
        minesPositions.add(new Pair<>(0, 2));
        minesPositions.add(new Pair<>(1, 0));
        minesPositions.add(new Pair<>(2, 0));

        // - 0 1 2
        // 0|*|*|*|
        // 1|*|5|2|
        // 2|*|2|0|
        logicsImpl = new LogicsImpl(new FixedMinePlacer(3, minesPositions));
        logicsImpl.hit(new Pair<>(2, 2));
        assertAll(() -> assertTrue(logicsImpl.getCell(new Pair<>(1, 1)).wasHit()),
                () -> assertTrue(logicsImpl.getCell(new Pair<>(1, 2)).wasHit()),
                () -> assertTrue(logicsImpl.getCell(new Pair<>(2, 1)).wasHit()));
        assertAll(minesPositions
                .stream()
                .map((p) -> () -> assertFalse(logicsImpl.getCell(p).wasHit())));
    }

    @Test
    void allCellsAreInitiallyUnflagged() {
        Set<Pair<Integer, Integer>> allPositions = new HashSet<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Pair<Integer, Integer> position = new Pair<>(row, column);
                allPositions.add(position);
            }
        }

        assertAll(allPositions
                .stream()
                .map(position -> logicsImpl.getCell(position))
                .map((cell) -> {
                    return () -> assertFalse(cell.hasFlag());
                }));
    }

    @Test
    void setFlag() {
        var cell = logicsImpl.getCell(new Pair<>(0, 0));
        cell.setFlag(true);
        assertTrue(cell.hasFlag());
    }

    @Test
    void unsetFlag() {
        var cell = logicsImpl.getCell(new Pair<>(0, 0));
        cell.setFlag(true);
        cell.setFlag(false);
        assertFalse(cell.hasFlag());
    }

    @Test
    void toggleFlag() {
        var position = new Pair<>(0, 0);
        var cell = logicsImpl.getCell(position);
        var initialValue = cell.hasFlag();
        logicsImpl.toggleFlag(position);
        assertEquals(!initialValue, cell.hasFlag());
    }
}
