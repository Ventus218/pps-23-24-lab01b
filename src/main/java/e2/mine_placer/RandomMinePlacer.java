package e2.mine_placer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import e2.Pair;

public class RandomMinePlacer extends AbstractMinePlacer {
    Random random;

    public RandomMinePlacer(long seed) {
        this.random = new Random(seed);
    }

    public RandomMinePlacer() {
        this.random = new Random();
    }

    @Override
    protected Set<Pair<Integer, Integer>> placeMinesImplementation(int numberOfMinesToPlace, int boardSize) {
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        while (mines.size() < numberOfMinesToPlace) {
            mines.add(new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize)));
        }
        return mines;
    }
}
