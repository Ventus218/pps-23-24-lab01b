package e2.mine_placer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import e2.Pair;

public class RandomMinePlacer implements MinePlacer {
    Random random;

    public RandomMinePlacer(long seed) {
        this.random = new Random(seed);
    }

    public RandomMinePlacer() {
        this.random = new Random();
    }

    @Override
    public Set<Pair<Integer, Integer>> placeMines(int numberOfMinesToPlace, int boardSize) {
        if (boardSize < 1) {
            throw new IllegalArgumentException("Board size can't be smaller than 1");
        }
        if (numberOfMinesToPlace < 0) {
            throw new IllegalArgumentException("Can't place a negative amount of mines");
        }
        if (numberOfMinesToPlace > boardSize * boardSize) {
            throw new IllegalArgumentException("Too many mines for a board with size " + boardSize);
        }
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        while (mines.size() < numberOfMinesToPlace) {
            mines.add(new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize)));
        }
        return mines;
    }

}
