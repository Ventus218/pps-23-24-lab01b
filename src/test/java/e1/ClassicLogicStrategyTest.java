package e1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassicLogicStrategyTest {

    LogicStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new ClassicLogicStrategy();
    }

    @Test
    void cannotMoveToCurrentPosition() {
        Pair<Integer, Integer> currentPosition = new Pair<>(0, 0);
        assertFalse(strategy.canMove(currentPosition, currentPosition));
    }

    @Test
    void canMoveToAllowedDestinations() {
        Pair<Integer, Integer> currentPosition = new Pair<>(2, 2);
        List<Pair<Integer, Integer>> allowedDestinations = new ArrayList<>();
        allowedDestinations.add(new Pair<>(0, 1));
        allowedDestinations.add(new Pair<>(0, 3));
        allowedDestinations.add(new Pair<>(1, 0));
        allowedDestinations.add(new Pair<>(1, 4));
        allowedDestinations.add(new Pair<>(3, 0));
        allowedDestinations.add(new Pair<>(3, 4));
        allowedDestinations.add(new Pair<>(4, 1));
        allowedDestinations.add(new Pair<>(4, 3));

        assertAll(allowedDestinations
                .stream()
                .map(destination -> {
                    return () -> assertTrue(strategy.canMove(currentPosition, destination));
                }));
    }

    @Test
    void cannotMoveToNotAllowedDestinations() {
        Pair<Integer, Integer> currentPosition = new Pair<>(2, 2);
        List<Pair<Integer, Integer>> allowedDestinations = new ArrayList<>();
        allowedDestinations.add(new Pair<>(0, 1));
        allowedDestinations.add(new Pair<>(0, 3));
        allowedDestinations.add(new Pair<>(1, 0));
        allowedDestinations.add(new Pair<>(1, 4));
        allowedDestinations.add(new Pair<>(3, 0));
        allowedDestinations.add(new Pair<>(3, 4));
        allowedDestinations.add(new Pair<>(4, 1));
        allowedDestinations.add(new Pair<>(4, 3));

        // Generating every non-allowed destination is impossible due to the fact that
        // the strategy doesn't make any assumption on the board size.
        var size = 5;
        List<Pair<Integer, Integer>> notAllowedDestinations = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var destination = new Pair<>(x, y);
                if (!allowedDestinations.contains(destination)) {
                    notAllowedDestinations.add(destination);
                }
            }
        }

        assertAll(notAllowedDestinations
                .stream()
                .map(destination -> {
                    return () -> assertFalse(strategy.canMove(currentPosition, destination));
                }));
    }
}
