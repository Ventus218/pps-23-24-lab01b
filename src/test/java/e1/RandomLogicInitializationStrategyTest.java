package e1;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomLogicInitializationStrategyTest {

    protected static final int SEED = 1234;

    // In order to test randomicity may be a good idea to make many calls and test
    // that at least one of them is different from all the others.
    // Also using a large boardSize reduces the probability of getting the same
    // initialization multiple times.
    protected static final int BOARD_SIZE = 8;
    protected static final int NUMBER_OF_SUBSEQUENT_CALLS = 10;

    protected LogicInitializationStrategy strategy;

    protected LogicInitializationStrategy makeLogicInitializationStrategy() {
        return new RandomLogicInitializationStrategy(SEED);
    }

    @BeforeEach
    void init() {
        strategy = makeLogicInitializationStrategy();
    }

    @Test
    void subsequentInitializationsReturnDifferentInitializationConfigurations() {
        LogicInitializationConfiguration configuration = strategy.initializeConfiguration(BOARD_SIZE);
        for (int i = 0; i < NUMBER_OF_SUBSEQUENT_CALLS; i++) {
            if (!configuration.equals(strategy.initializeConfiguration(BOARD_SIZE))) {
                return;
            }
        }
        fail(NUMBER_OF_SUBSEQUENT_CALLS + " subsequent calls returned always the same configuration");
    }
}
