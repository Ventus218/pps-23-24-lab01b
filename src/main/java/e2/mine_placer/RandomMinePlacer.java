package e2.mine_placer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import e2.Pair;

public class RandomMinePlacer implements MinePlacer {
    Random random;
    private int boardSize;
    private int numberOfMinesToPlace;

    public RandomMinePlacer(int boardSize, int numberOfMinesToPlace, long seed) {
        MinePlacer.checkPlaceMinesArguments(boardSize, numberOfMinesToPlace);
        this.boardSize = boardSize;
        this.numberOfMinesToPlace = numberOfMinesToPlace;
        this.random = new Random(seed);
    }

    public RandomMinePlacer(int boardSize, int numberOfMinesToPlace) {
        MinePlacer.checkPlaceMinesArguments(boardSize, numberOfMinesToPlace);
        this.boardSize = boardSize;
        this.numberOfMinesToPlace = numberOfMinesToPlace;
        this.random = new Random();
    }

    @Override
    public Set<Pair<Integer, Integer>> placeMines() {
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        while (mines.size() < numberOfMinesToPlace) {
            mines.add(new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize)));
        }
        return mines;
    }

    @Override
    public int numberOfMinesToPlace() {
        return numberOfMinesToPlace;
    }

    @Override
    public int boardSize() {
        return boardSize;
    }
}
