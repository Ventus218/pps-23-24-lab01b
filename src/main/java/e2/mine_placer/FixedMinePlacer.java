package e2.mine_placer;

import java.util.Collections;
import java.util.Set;

import e2.Pair;

public class FixedMinePlacer implements MinePlacer {

    private final Set<Pair<Integer, Integer>> minesPositions;
    private final int boardSize;

    public FixedMinePlacer(int boardSize, Set<Pair<Integer, Integer>> minesPositions) {
        MinePlacer.checkPlaceMinesArguments(boardSize, minesPositions.size());
        this.boardSize = boardSize;
        this.minesPositions = Set.copyOf(minesPositions);
    }

    @Override
    public Set<Pair<Integer, Integer>> placeMines() {
        return Collections.unmodifiableSet(minesPositions);
    }

    @Override
    public int numberOfMinesToPlace() {
        return minesPositions.size();
    }

    @Override
    public int boardSize() {
        return boardSize;
    }
}
