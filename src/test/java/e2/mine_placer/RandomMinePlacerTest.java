package e2.mine_placer;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import e2.Pair;

public class RandomMinePlacerTest extends MinePlacerTest {

    private static final int CALLS_TO_TEST_RANDOMICITY = 20;

    @Override
    MinePlacer makeMinePlacer() {
        long seed = 1234;
        return new RandomMinePlacer(seed);
    }

    @Test
    void subsequentCallsToPlaceMinesReturnDifferentMinesConfigurations() {
        Supplier<Set<Pair<Integer, Integer>>> placeMines = () -> minePlacer.placeMines(numberOfMinesToPlace(),
                boardSize());
        var mines = placeMines.get();
        for (int i = 0; i < CALLS_TO_TEST_RANDOMICITY; i++) {
            if (!mines.equals(placeMines.get())) {
                return;
            }
        }
        fail(CALLS_TO_TEST_RANDOMICITY + " calls returned always the same mine configuration.");
    }
}
