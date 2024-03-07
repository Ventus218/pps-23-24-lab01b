package e2.mine_placer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.Pair;

public abstract class MinePlacerTest {

    protected MinePlacer minePlacer;

    abstract MinePlacer makeMinePlacer();

    protected int numberOfMinesToPlace() {
        return 7;
    }

    protected int boardSize() {
        return 7;
    }

    @BeforeEach
    void init() {
        minePlacer = makeMinePlacer();
    }

    @Test
    void placesTheCorrectNumberOfMines() {
        Set<Pair<Integer, Integer>> mines = minePlacer.placeMines(numberOfMinesToPlace(), boardSize());
        assertEquals(numberOfMinesToPlace(), mines.size());
    }

    @Test
    void throwsIfBoardSizeIsSmallerThanOne() {
        assertThrows(IllegalArgumentException.class, () -> minePlacer.placeMines(numberOfMinesToPlace(), 0));
    }

    @Test
    void throwsIfNumberOfMinesToPlaceIsSmallerThanZero() {
        assertThrows(IllegalArgumentException.class, () -> minePlacer.placeMines(-1, boardSize()));
    }

    @Test
    void throwsIfNumberOfMinesExceedBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> minePlacer.placeMines(boardSize() * boardSize() + 1, boardSize()));
    }
}
