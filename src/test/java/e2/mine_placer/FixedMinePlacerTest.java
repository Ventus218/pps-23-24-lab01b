package e2.mine_placer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

import e2.Pair;

public class FixedMinePlacerTest extends MinePlacerTest {

    Set<Pair<Integer, Integer>> minesPositions;

    FixedMinePlacerTest() {
        minesPositions = new HashSet<>();
        minesPositions.add(new Pair<>(0, 0));
        minesPositions.add(new Pair<>(0, 1));
        minesPositions.add(new Pair<>(0, 2));
        minesPositions.add(new Pair<>(2, 1));
    }

    @Override
    protected int numberOfMinesToPlace() {
        return minesPositions.size();
    }

    @Override
    protected int boardSize() {
        return 3;
    }

    @Override
    MinePlacer makeMinePlacer() {
        return new FixedMinePlacer(minesPositions);
    }

}
