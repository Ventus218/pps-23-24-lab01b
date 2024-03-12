package e2;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import e2.grid.GridWithAdjacenceImpl;
import e2.mine_placer.MinePlacer;
import e2.mine_placer.RandomMinePlacer;

public class LogicsImpl implements Logics {

    private final GridWithAdjacenceImpl<MineSweeperCell> grid;

    public LogicsImpl(MinePlacer minePlacer) {
        if (minePlacer.numberOfMinesToPlace() >= Math.pow(minePlacer.boardSize(), 2)) {
            throw new IllegalArgumentException("Mines cannot completely cover the board");
        }
        grid = new GridWithAdjacenceImpl<>(minePlacer.boardSize());
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
            var mineSweeperCell = cell.get();
            if (mineSweeperCell.wasHit()) {
                throw new IllegalStateException("Can't hit same cell twice");
            }
            mineSweeperCell.hit();
            if (mineSweeperCell.hasMine()) {
                return true;
            }
            if (numberOfAdjacentMines(position) == 0) {
                for (var adjacentCell : mineSweeperCell.adjacentAddressables()) {
                    if (!adjacentCell.wasHit()) {
                        hit(new Pair<>(adjacentCell.getX(), adjacentCell.getY()));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int numberOfAdjacentMines(Pair<Integer, Integer> position) {
        if (mines().contains(position)) {
            throw new IllegalStateException("numberOfAdjacentMines cannot be called on a mine");
        }
        var cell = grid.get(position.getX(), position.getY()).get();
        return Math.toIntExact(cell.adjacentAddressables()
                .stream()
                .filter(mineSweeperCell -> mineSweeperCell.hasMine())
                .count());
    }

    @Override
    public MineSweeperCell getCell(Pair<Integer, Integer> position) {
        return grid.get(position.getX(), position.getY()).get();
    }

    @Override
    public void toggleFlag(Pair<Integer, Integer> position) {
        var cell = getCell(position);
        cell.setFlag(!cell.hasFlag());
    }

}
