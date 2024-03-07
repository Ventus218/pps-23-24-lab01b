package e2.mine_placer;

import java.util.Collections;
import java.util.Set;

import e2.Pair;

public class FixedMinePlacer extends AbstractMinePlacer {

    private final Set<Pair<Integer, Integer>> minesPositions;

    public FixedMinePlacer(Set<Pair<Integer, Integer>> minesPositions) {
        this.minesPositions = Set.copyOf(minesPositions);
    }

    @Override
    protected Set<Pair<Integer, Integer>> placeMinesImplementation(int numberOfMinesToPlace, int boardSize) {
        return Collections.unmodifiableSet(minesPositions);
    }

}
