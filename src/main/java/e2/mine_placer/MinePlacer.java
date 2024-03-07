package e2.mine_placer;

import java.util.Set;

import e2.Pair;

public interface MinePlacer {

    Set<Pair<Integer, Integer>> placeMines();

    int numberOfMinesToPlace();

    int boardSize();

    static void checkPlaceMinesArguments(int boardSize, int numberOfMinesToPlace) {
        if (boardSize < 1) {
            throw new IllegalArgumentException("Board size can't be smaller than 1");
        }
        if (numberOfMinesToPlace < 0) {
            throw new IllegalArgumentException("Can't place a negative amount of mines");
        }
        if (numberOfMinesToPlace > boardSize * boardSize) {
            throw new IllegalArgumentException("Too many mines for a board with size " + boardSize);
        }
    }
}
