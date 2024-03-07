package e2.mine_placer;

import java.util.Set;

import e2.Pair;

public interface MinePlacer {

    Set<Pair<Integer, Integer>> placeMines(int numberOfMinesToPlace, int boardSize);

}
