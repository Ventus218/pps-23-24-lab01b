package e1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicInitializationConfigurationImplTest {

    private LogicInitializationConfiguration logicInitializationConfiguration;
    private static final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(0, 0);
    private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(1, 1);

    @BeforeEach
    void init() {
        logicInitializationConfiguration = new LogicInitializationConfigurationImpl(KNIGHT_POSITION, PAWN_POSITION);
    }

    @Test
    void getKnightPosition() {
        KNIGHT_POSITION.equals(logicInitializationConfiguration.knightPosition());
    }

    @Test
    void getPawnPosition() {
        PAWN_POSITION.equals(logicInitializationConfiguration.pawnPosition());
    }

    @Test
    void testSuccessfulEquals() {
        assertTrue(logicInitializationConfiguration
                .equals(new LogicInitializationConfigurationImpl(KNIGHT_POSITION, PAWN_POSITION)));
    }

    @Test
    void testUnsuccessfulEquals() {
        assertFalse(logicInitializationConfiguration
                .equals(new LogicInitializationConfigurationImpl(new Pair<>(2, 2), new Pair<>(3, 3))));
    }
}
