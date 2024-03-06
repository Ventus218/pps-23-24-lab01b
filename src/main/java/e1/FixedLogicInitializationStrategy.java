package e1;

public class FixedLogicInitializationStrategy implements LogicInitializationStrategy {

    private final Pair<Integer, Integer> knightInitialPosition;
    private final Pair<Integer, Integer> pawnInitialPosition;

    public FixedLogicInitializationStrategy(Pair<Integer, Integer> knightInitialPosition,
            Pair<Integer, Integer> pawnInitialPosition) {
        if (knightInitialPosition.getX() < 0 ||
                knightInitialPosition.getY() < 0 ||
                pawnInitialPosition.getX() < 0 ||
                pawnInitialPosition.getY() < 0) {
            throw new IllegalArgumentException("Only positive numbers are allowed for positions coordinates");
        }

        if (knightInitialPosition.equals(pawnInitialPosition)) {
            throw new IllegalArgumentException("Pawn and Knight cannot be initialized on same position");
        }

        this.knightInitialPosition = knightInitialPosition;
        this.pawnInitialPosition = pawnInitialPosition;
    }

    @Override
    public LogicInitializationConfiguration initializeConfiguration(int boardSize) {
        if (knightInitialPosition.getX() >= boardSize ||
                knightInitialPosition.getY() >= boardSize ||
                pawnInitialPosition.getX() >= boardSize ||
                pawnInitialPosition.getY() >= boardSize) {
            throw new IllegalArgumentException("Board size not compatible with given positions");
        }
        return new LogicInitializationConfigurationImpl(knightInitialPosition, pawnInitialPosition);
    }

}
