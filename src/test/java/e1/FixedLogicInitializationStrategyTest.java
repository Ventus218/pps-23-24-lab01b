package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FixedLogicInitializationStrategyTest extends LogicInitializationStrategyTest {

    private final Pair<Integer, Integer> initialKnightPosition = new Pair<>(0, 0);
    private final Pair<Integer, Integer> initialPawnPosition = new Pair<>(2, 2);

    @Override
    LogicInitializationStrategy makeLogicInitializationStrategy() {
        return new FixedLogicInitializationStrategy(initialKnightPosition, initialPawnPosition);
    }

    @Test
    void negativeCoordinatesAreNotAllowed() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedLogicInitializationStrategy(new Pair<>(-1, 0), new Pair<>(0, 0)));
    }

    @Test
    void knightAndPawnOnSamePositionNotAllowed() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedLogicInitializationStrategy(new Pair<>(0, 0), new Pair<>(0, 0)));
    }

    @Test
    void initializeConfigurationReturnsCorrectConfiguration() {
        LogicInitializationConfiguration configuration = strategy.initializeConfiguration(BOARD_SIZE);
        assertAll(() -> assertEquals(initialKnightPosition, configuration.knightPosition()),
                () -> assertEquals(initialPawnPosition, configuration.pawnPosition()));
    }

    @Test
    void subsequentInitializationsReturnSameConfigurations() {
        LogicInitializationConfiguration configuration = strategy.initializeConfiguration(BOARD_SIZE);
        for (int i = 0; i < NUMBER_OF_SUBSEQUENT_CALLS; i++) {
            if (!configuration.equals(strategy.initializeConfiguration(BOARD_SIZE))) {
                fail(NUMBER_OF_SUBSEQUENT_CALLS + " subsequent calls returned different configurations");
            }
        }
    }

    @Test
    void initializeConfigurationThrowsIfBoardSizeIsIncompatibleWithGivenPositions() {
        assertThrows(IllegalArgumentException.class, () -> strategy.initializeConfiguration(2));
    }
}
