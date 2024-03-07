package e2.mine_placer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.function.BiFunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.Pair;

public abstract class MinePlacerTest {

    protected MinePlacer minePlacer;

    abstract MinePlacer makeMinePlacer();

    @BeforeEach
    void init() {
        minePlacer = makeMinePlacer();
    }

    @Test
    void placesTheCorrectNumberOfMines() {
        Set<Pair<Integer, Integer>> mines = minePlacer.placeMines();
        assertEquals(minePlacer.numberOfMinesToPlace(), mines.size());
    }

    @Test
    void placedMinesDoNotExceedBoardSize() {
        BiFunction<Pair<Integer, Integer>, Integer, Boolean> minePlacementRespectsBoardSizeBounds = (mine,
                boardSize) -> {
            return mine.getX() >= 0 &&
                    mine.getY() >= 0 &&
                    mine.getX() < boardSize &&
                    mine.getY() < boardSize;
        };
        assertAll(minePlacer.placeMines()
                .stream()
                .map((mine) -> () -> assertTrue(
                        minePlacementRespectsBoardSizeBounds.apply(mine, minePlacer.boardSize()))));
    }

    abstract void throwsIfBoardSizeIsSmallerThanOne();

    abstract void throwsIfNumberOfMinesToPlaceIsSmallerThanZero();

    abstract void throwsIfNumberOfMinesExceedBoardSize();
}
