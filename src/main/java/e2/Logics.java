package e2;

import java.util.Set;

public interface Logics {

    Set<Pair<Integer, Integer>> mines();

    boolean hit(Pair<Integer, Integer> position);

    int numberOfAdjacentMines(Pair<Integer, Integer> position);

    MineSweeperCell getCell(Pair<Integer, Integer> pair);

    void toggleFlag(Pair<Integer, Integer> position);

}
