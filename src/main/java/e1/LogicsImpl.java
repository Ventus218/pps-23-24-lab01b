package e1;

import java.util.*;

public class LogicsImpl implements Logics {

	private final Pair<Integer, Integer> pawn;
	private Pair<Integer, Integer> knight;
	private final Random random = new Random();
	private final int size;
	private final LogicStrategy logicStrategy;

	public LogicsImpl(int size, LogicStrategy logicStrategy) {
		this.size = size;
		this.logicStrategy = logicStrategy;
		this.pawn = this.randomEmptyPosition();
		this.knight = this.randomEmptyPosition();
	}

	LogicsImpl(int size, LogicStrategy logicStrategy, Pair<Integer, Integer> knightInitialPosition,
			Pair<Integer, Integer> pawnInitialPosition) {
		if (knightInitialPosition.equals(pawnInitialPosition)) {
			throw new IllegalArgumentException("Pawn and Knight cannot be initialized on same position");
		}

		final int minimumBoardSize = 2;
		if (size < minimumBoardSize) {
			throw new IllegalArgumentException("Size cannot be lower than " + minimumBoardSize);
		}

		this.size = size;
		this.logicStrategy = logicStrategy;
		this.knight = knightInitialPosition;
		this.pawn = pawnInitialPosition;
	}

	private final Pair<Integer, Integer> randomEmptyPosition() {
		Pair<Integer, Integer> pos = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
		// the recursive call below prevents clash with an existing pawn
		return this.pawn != null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
	}

	@Override
	public boolean hit(int row, int col) {
		if (row < 0 || col < 0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}

		if (this.logicStrategy.canMove(this.knight, new Pair<>(row, col))) {
			this.knight = new Pair<>(row, col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row, col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row, col));
	}
}
