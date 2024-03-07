package e2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import e2.mine_placer.MinePlacer;
import e2.mine_placer.RandomMinePlacer;

public class LogicsImpl implements Logics {

    private final int size;
    private final int numberOfMines;
    private final Set<Pair<Integer, Integer>> mines;

    public LogicsImpl(int size, int numberOfMines, MinePlacer minePlacer) {
        if (numberOfMines >= size * size) {
            throw new IllegalArgumentException("Mines cannot completely cover the board");
        }
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.mines = minePlacer.placeMines(numberOfMines, size);
    }

    public LogicsImpl(int size, int numberOfMines) {
        this(size, numberOfMines, new RandomMinePlacer());
    }

    @Override
    public Set<Pair<Integer, Integer>> mines() {
        return Collections.unmodifiableSet(mines);
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        return mines().contains(position);
    }
}
