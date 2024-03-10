package e2;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import e2.grid.Grid;
import e2.grid.GridImpl;
import e2.mine_placer.MinePlacer;
import e2.mine_placer.RandomMinePlacer;

public class LogicsImpl implements Logics {

    private final Grid grid;

    public LogicsImpl(MinePlacer minePlacer) {
        if (minePlacer.numberOfMinesToPlace() >= Math.pow(minePlacer.boardSize(), 2)) {
            throw new IllegalArgumentException("Mines cannot completely cover the board");
        }
        grid = new GridImpl(minePlacer.boardSize());
        createCells(minePlacer);
    }

    public LogicsImpl(int size, int numberOfMines) {
        this(new RandomMinePlacer(size, numberOfMines));
    }

    private void createCells(MinePlacer minePlacer) {
        var mines = minePlacer.placeMines();
        for (int x = 0; x < minePlacer.boardSize(); x++) {
            for (int y = 0; y < minePlacer.boardSize(); y++) {
                var position = new Pair<>(x, y);
                grid.add(new MineSweeperCell(position, mines.contains(position)));
            }
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> mines() {
        return Collections
                .unmodifiableSet(grid.getAll()
                        .stream()
                        .map(cell -> (MineSweeperCell) cell)
                        .filter(cell -> cell.hasMine())
                        .map(cell -> new Pair<>(cell.getX(), cell.getY()))
                        .collect(Collectors.toSet()));
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        var x = position.getX();
        var y = position.getY();
        var cell = grid.get(x, y);
        if (cell.isPresent()) {
            var mineSweeperCell = (MineSweeperCell) cell.get();
            mineSweeperCell.hit();
            return mineSweeperCell.hasMine();
        }
        return false;
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
