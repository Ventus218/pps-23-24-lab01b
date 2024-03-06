package e1;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class RandomLogicInitializationStrategyTest {

    @Test
    void subsequentInitializationsReturnDifferentInitializationConfigurations() {
        // In order to test randomicity may be a good idea to make many calls and test
        // that at least one of them is different from all the others.
        // Also using a large boardSize reduces the probability of getting the same
        // initialization multiple times.

        var seed = 1234;
        int boardSize = 8;
        int calls = 10;
        LogicInitializationStrategy strategy = new RandomLogicInitializationStrategy(seed);

        LogicInitializationConfiguration configuration = strategy.initializeConfiguration(boardSize);
        for (int i = 0; i < calls; i++) {
            if (!configuration.equals(strategy.initializeConfiguration(boardSize))) {
                return;
            }
        }
        fail(calls + " subsequent calls returned always the same configuration");
    }
}
