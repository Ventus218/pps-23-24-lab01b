package e1;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class RandomLogicInitializationStrategyTest extends LogicInitializationStrategyTest {

    @Override
    LogicInitializationStrategy makeLogicInitializationStrategy() {
        return new RandomLogicInitializationStrategy(SEED);
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
