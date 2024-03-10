package e2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MineSweeperCellTest {
    private static final int X = 1;
    private static final int Y = 1;
    private static final boolean HAS_MINE = true;
    private final Pair<Integer, Integer> position = new Pair<>(X, Y);
    private MineSweeperCell cell;

    @BeforeEach
    void init() {
        cell = new MineSweeperCell(position, HAS_MINE);
    }

    @Test
    void hasMine() {
        assertEquals(HAS_MINE, cell.hasMine());
    }

    @Test
    void hasNotMine() {
        var hasMine = !HAS_MINE;
        cell = new MineSweeperCell(cell.getX(), cell.getY(), hasMine);
        assertEquals(hasMine, cell.hasMine());
    }

}
