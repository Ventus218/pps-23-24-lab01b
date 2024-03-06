package e1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ClassicLogicInitializationStrategyTest extends RandomLogicInitializationStrategyTest {

    @Override
    protected LogicInitializationStrategy makeLogicInitializationStrategy() {
        return new ClassicLogicInitializationStrategy(SEED);
    }

    @Test
    @Timeout(30)
    void throwsWhenBoardIsTooSmallForThisStrategy() {
        assertThrows(IllegalArgumentException.class, () -> strategy.initializeConfiguration(1));
    }

    @Test
    @Timeout(30)
    void knightAndPawnPositionAreNotTheSame() {
        for (int i = 0; i < NUMBER_OF_SUBSEQUENT_CALLS; i++) {
            LogicInitializationConfiguration configuration = strategy.initializeConfiguration(2);
            assertNotEquals(configuration.knightPosition(), configuration.pawnPosition());
        }
    }
}
