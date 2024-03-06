package e1;

import org.junit.jupiter.api.BeforeEach;

public abstract class LogicInitializationStrategyTest {
    protected static final int SEED = 1234;

    // In order to test randomicity may be a good idea to make many calls and test
    // that at least one of them is different from all the others.
    // Also using a large boardSize reduces the probability of getting the same
    // initialization multiple times.
    protected static final int BOARD_SIZE = 8;
    protected static final int NUMBER_OF_SUBSEQUENT_CALLS = 10;

    protected LogicInitializationStrategy strategy;

    abstract LogicInitializationStrategy makeLogicInitializationStrategy();

    @BeforeEach
    void init() {
        strategy = makeLogicInitializationStrategy();
    }
}
