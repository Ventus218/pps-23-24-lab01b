package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClassicLogicsImplTest extends LogicTest {

    @Override
    int boardSize() {
        return 5;
    }

    @Override
    Logics makeLogics() {
        return new LogicsImpl(boardSize(), new ClassicLogicStrategy(),
                new ClassicLogicInitializationStrategy(1234));
    }

    @Test
    void knightHitsPawn() {
        var pawnPosition = new Pair<Integer, Integer>(0, 0);
        var knightPosition = new Pair<Integer, Integer>(1, 2);
        logics = new LogicsImpl(boardSize(), new ClassicLogicStrategy(), new FixedLogicInitializationStrategy(knightPosition, pawnPosition));

        assertAll(
                () -> assertTrue(logics.hit(pawnPosition.getX(), pawnPosition.getY())),
                () -> assertTrue(logics.hasKnight(pawnPosition.getX(), pawnPosition.getY())));
    }

    @Test
    void knightMissesPawn() {
        var pawnPosition = new Pair<Integer, Integer>(0, 0);
        var knightPosition = new Pair<Integer, Integer>(1, 2);
        var hitPosition = new Pair<Integer, Integer>(2, 0);
        logics = new LogicsImpl(boardSize(), new ClassicLogicStrategy(), new FixedLogicInitializationStrategy(knightPosition, pawnPosition));

        assertAll(
                () -> assertFalse(logics.hit(hitPosition.getX(), hitPosition.getY())),
                () -> assertTrue(logics.hasKnight(hitPosition.getX(), hitPosition.getY())));
    }

    @Test
    void knightMovementPatternIsCorrect() {
        var boardSize = 5;
        var initialKnightPosition = new Pair<>(2, 2);
        var pawnPosition = new Pair<>(0, 0);

        // T indicates the positions where the knight is allowed to move.
        // Moving to any other position is not allowed.
        //
        // In order to test this behaviour the pawn must be placed in a position where
        // the knight can't move
        //
        // .. 0 1 2 3 4
        // 0 | |T| |T| |
        // 1 |T| | | |T|
        // 2 | | |K| | |
        // 3 |T| | | |T|
        // 4 | |T| |T| |

        var allowedPositions = resultForEachPosition(position -> {
            return position.equals(new Pair<>(0, 1)) ||
                    position.equals(new Pair<>(0, 3)) ||
                    position.equals(new Pair<>(1, 0)) ||
                    position.equals(new Pair<>(1, 4)) ||
                    position.equals(new Pair<>(3, 0)) ||
                    position.equals(new Pair<>(3, 4)) ||
                    position.equals(new Pair<>(4, 1)) ||
                    position.equals(new Pair<>(4, 3));
        }, boardSize);

        var actualPositions = resultForEachPosition(position -> {
            logics = new LogicsImpl(boardSize, new ClassicLogicStrategy(), new FixedLogicInitializationStrategy(initialKnightPosition, pawnPosition));
            var x = position.getX();
            var y = position.getY();
            logics.hit(x, y);
            return logics.hasKnight(x, y) && !position.equals(initialKnightPosition);
        }, boardSize);

        assertEquals(allowedPositions, actualPositions);
    }
}