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
    Boolean hasKnight = false;

    var resultMap = resultForEachPosition((var position) -> {
      return logics.hasKnight(position.getX(), position.getY());
    });

    for (var result : resultMap.values()) {
      if (result) {
        if (hasKnight) {
          fail("board has multiple knights");
        } else {
          hasKnight = true;
        }
      }
    }

    assertTrue(hasKnight);
  }

  @Test
  void hasOneAndOnlyOnePawn() {
    Boolean hasPawn = false;

    var resultMap = resultForEachPosition((var position) -> {
      return logics.hasPawn(position.getX(), position.getY());
    });

    for (var result : resultMap.values()) {
      if (result) {
        if (hasPawn) {
          fail("board has multiple pawns");
        } else {
          hasPawn = true;
        }
      }
    }

    assertTrue(hasPawn);
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
