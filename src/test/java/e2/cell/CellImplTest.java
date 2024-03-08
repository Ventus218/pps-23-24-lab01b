package e2.cell;

import e2.Pair;

public class CellImplTest extends CellTest {

    private static final int X = 1;
    private static final int Y = 1;
    private final Pair<Integer, Integer> position = new Pair<>(X, Y);

    @Override
    Cell makeCell() {
        return new CellImpl(position);
    }

    @Override
    Pair<Integer, Integer> position() {
        return position;
    }

}
