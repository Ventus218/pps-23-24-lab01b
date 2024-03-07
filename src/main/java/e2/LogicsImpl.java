package e2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final int size;
    private final int numberOfMines;
    private final Set<Pair<Integer, Integer>> mines;

    public LogicsImpl(int size, int numberOfMines) {
        if (numberOfMines >= size * size) {
            throw new IllegalArgumentException("Mines cannot completely cover the board");
        }
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = randomlyPlacedMines();
    }

    private Set<Pair<Integer, Integer>> randomlyPlacedMines() {
        var random = new Random();
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        while (mines.size() < numberOfMines) {
            mines.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
        }
        return mines;
    }

    @Override
    public Set<Pair<Integer, Integer>> mines() {
        return Collections.unmodifiableSet(mines);
    }

}
