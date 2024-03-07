package e2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import e2.mine_placer.MinePlacer;
import e2.mine_placer.RandomMinePlacer;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> mines;

    public LogicsImpl(MinePlacer minePlacer) {
        if (minePlacer.numberOfMinesToPlace() >= Math.pow(minePlacer.boardSize(), 2)) {
            throw new IllegalArgumentException("Mines cannot completely cover the board");
        }
        this.mines = minePlacer.placeMines();
    }

    public LogicsImpl(int size, int numberOfMines) {
        this(new RandomMinePlacer(size, numberOfMines));
    }

    @Override
    public Set<Pair<Integer, Integer>> mines() {
        return Collections.unmodifiableSet(mines);
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        return mines().contains(position);
    }

    @Override
    public int numberOfAdjacentMines(Pair<Integer, Integer> position) {
        if (mines().contains(position)) {
            throw new IllegalStateException("numberOfAdjacentMines cannot be called on a mine");
        }
        return Math.toIntExact(mines()
                .stream()
                .filter(mine -> {
                    var xDelta = Math.abs(mine.getX() - position.getX());
                    var yDelta = Math.abs(mine.getY() - position.getY());
                    var delta = xDelta + yDelta;
                    return delta != 0 && xDelta <= 1 && yDelta <= 1;
                })
                .count());
    }
}
