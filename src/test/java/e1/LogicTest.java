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

  /*
   * Returns a mapping from every position of the board to the result of the given
   * function for that position
   */
  <T> Map<Pair<Integer, Integer>, T> resultForEachPosition(Function<Pair<Integer, Integer>, T> function) {
    Map<Pair<Integer, Integer>, T> map = new HashMap<>();
    for (int row = 0; row < SIZE; row++) {
      for (int column = 0; column < SIZE; column++) {
        var position = new Pair<>(row, column);
        map.put(position, function.apply(position));
      }
    }
    return map;
  }
}
