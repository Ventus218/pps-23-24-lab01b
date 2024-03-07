package e2.mine_placer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import e2.Pair;

public class RandomMinePlacerTest extends MinePlacerTest {

    private static final int CALLS_TO_TEST_RANDOMICITY = 20;
    private static final int NUMBER_OF_MINES = 7;
    private static final int BOARD_SIZE = 7;

    @Override
    MinePlacer makeMinePlacer() {
        long seed = 1234;
        return new RandomMinePlacer(BOARD_SIZE, NUMBER_OF_MINES, seed);
    }

    @Test
    void subsequentCallsToPlaceMinesReturnDifferentMinesConfigurations() {
        Supplier<Set<Pair<Integer, Integer>>> placeMines = () -> minePlacer.placeMines();
        var mines = placeMines.get();
        for (int i = 0; i < CALLS_TO_TEST_RANDOMICITY; i++) {
            if (!mines.equals(placeMines.get())) {
                return;
            }
        }
        fail(CALLS_TO_TEST_RANDOMICITY + " calls returned always the same mine configuration.");
    }

    @Override
    @Test
    void throwsIfBoardSizeIsSmallerThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new RandomMinePlacer(0, 0));
    }
    
    @Override
    @Test
    void throwsIfNumberOfMinesToPlaceIsSmallerThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new RandomMinePlacer(BOARD_SIZE, -1));
    }
    
    @Override
    @Test
    void throwsIfNumberOfMinesExceedBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomMinePlacer(BOARD_SIZE, BOARD_SIZE * BOARD_SIZE + 1));
    }
}
