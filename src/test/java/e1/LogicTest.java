package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class LogicTest {

  private Logics logics;
  private static final int SIZE = 3;

  @BeforeEach
  void init() {
    logics = new LogicsImpl(SIZE);
  }

  @Test
  void knightHitsPawn() {
    var pawnPosition = new Pair<Integer, Integer>(0, 0);
    var knightPosition = new Pair<Integer, Integer>(1, 2);
    logics = new LogicsImpl(SIZE, knightPosition, pawnPosition);

    assertAll(
        () -> assertTrue(logics.hit(pawnPosition.getX(), pawnPosition.getY())),
        () -> assertTrue(logics.hasKnight(pawnPosition.getX(), pawnPosition.getY())));
  }

  @Test
  void knightMissesPawn() {
    var pawnPosition = new Pair<Integer, Integer>(0, 0);
    var knightPosition = new Pair<Integer, Integer>(1, 2);
    var hitPosition = new Pair<Integer, Integer>(2, 0);
    logics = new LogicsImpl(SIZE, knightPosition, pawnPosition);

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
      logics = new LogicsImpl(boardSize, initialKnightPosition, pawnPosition);
      var x = position.getX();
      var y = position.getY();
      logics.hit(x, y);
      return logics.hasKnight(x, y) && !position.equals(initialKnightPosition);
    }, boardSize);

    assertEquals(allowedPositions, actualPositions);
  }

  @Test
  void hitThrowsWhenPositionIsOutOfBounds() {
    assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(0, SIZE)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(SIZE, 0)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(0, -1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(-1, 0)));
  }

  @Test
  void hasKnightAndHasPawnDoNotReturnTrueWithSameParameters() {
    var resultMap = resultForEachPosition((var position) -> {
      var x = position.getX();
      var y = position.getY();
      return logics.hasKnight(x, y) && logics.hasPawn(x, y);
    });

    assertFalse(resultMap.containsValue(true));
  }

  @Test
  void hasOneAndOnlyOneKnight() {
    var numberOfKnights = resultForEachPosition(position -> logics.hasKnight(position.getX(), position.getY()))
        .values()
        .stream()
        .filter(positionHasKnight -> positionHasKnight)
        .count();
    assertEquals(1, numberOfKnights);
  }

  @Test
  void hasOneAndOnlyOnePawn() {
    var numberOfPawns = resultForEachPosition(position -> logics.hasPawn(position.getX(), position.getY()))
        .values()
        .stream()
        .filter(positionHasPawn -> positionHasPawn)
        .count();
    assertEquals(1, numberOfPawns);
  }

  /**
   * Returns a mapping from every position of the board to the result of the given
   * function for that position.
   * Uses the test class SIZE
   * 
   * @param <T>
   * @param function
   * @return
   */
  <T> Map<Pair<Integer, Integer>, T> resultForEachPosition(Function<Pair<Integer, Integer>, T> function) {
    return resultForEachPosition(function, SIZE);
  }

  /**
   * Returns a mapping from every position of the board to the result of the given
   * function for that position
   * 
   * @param <T>
   * @param function
   * @param size
   * @return
   */
  <T> Map<Pair<Integer, Integer>, T> resultForEachPosition(Function<Pair<Integer, Integer>, T> function,
      int size) {
    Map<Pair<Integer, Integer>, T> map = new HashMap<>();
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        var position = new Pair<>(row, column);
        map.put(position, function.apply(position));
      }
    }
    return map;
  }
}
