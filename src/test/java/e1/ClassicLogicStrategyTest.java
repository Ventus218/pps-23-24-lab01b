package e1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ClassicLogicStrategyTest {

    @Test
    void cannotMoveToCurrentPosition() {
        LogicStrategy strategy = new ClassicLogicStrategy();
        Pair<Integer, Integer> currentPosition = new Pair<>(0, 0);
        assertFalse(strategy.canMove(currentPosition, currentPosition));
    }
}
