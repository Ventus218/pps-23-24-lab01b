package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class LogicsImplTest {
    private Logics logicsImpl;
    private static final int BOARD_SIZE = 7;
    private static final int NUMBER_OF_MINES = 7;
    private static final int CALLS_TO_TEST_RANDOMICITY = 20;

    @BeforeEach
    void init() {
        logicsImpl = new LogicsImpl(BOARD_SIZE, NUMBER_OF_MINES);
    }

    @Test
    void minesCannotCoverTheBoardCompletely() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(BOARD_SIZE, BOARD_SIZE * BOARD_SIZE));
    }

    @Test
    void numberOfPlacedMinesIsCorrect() {
        assertEquals(NUMBER_OF_MINES, logicsImpl.mines().size());
    }

    @Test
    void minesArePlacedRandomly() {
        Set<Pair<Integer, Integer>> mines = logicsImpl.mines();
        for (int i = 0; i < CALLS_TO_TEST_RANDOMICITY; i++) {
            init();
            if (!mines.equals(logicsImpl.mines())) {
                return;
            }
        }
        fail(CALLS_TO_TEST_RANDOMICITY + " calls returned always the same mine configuration.");
    }
}
