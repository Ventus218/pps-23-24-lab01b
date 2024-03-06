package e1;

public class LogicsImpl implements Logics {

	private final Pair<Integer, Integer> pawn;
	private Pair<Integer, Integer> knight;
	private final int size;
	private final LogicStrategy logicStrategy;

	public LogicsImpl(int size, LogicStrategy logicStrategy, LogicInitializationStrategy initializationStrategy) {
		this.size = size;
		this.logicStrategy = logicStrategy;
		var initializeConfiguration = initializationStrategy.initializeConfiguration(size);
		this.pawn = initializeConfiguration.pawnPosition();
		this.knight = initializeConfiguration.knightPosition();
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
