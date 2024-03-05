package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class LogicTest {

  protected Logics logics;

  abstract int boardSize();

  abstract Logics makeLogics();

  @BeforeEach
  void init() {
    logics = makeLogics();
  }

  @Test
  void hitThrowsWhenPositionIsOutOfBounds() {
    assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(0, boardSize())),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(boardSize(), 0)),
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
   * Uses the test class boardSize()
   * 
   * @param <T>
   * @param function
   * @return
   */
  <T> Map<Pair<Integer, Integer>, T> resultForEachPosition(Function<Pair<Integer, Integer>, T> function) {
    return resultForEachPosition(function, boardSize());
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
