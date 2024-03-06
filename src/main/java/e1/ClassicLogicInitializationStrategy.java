package e1;

public class ClassicLogicInitializationStrategy implements LogicInitializationStrategy {

    private RandomLogicInitializationStrategy randomLogicInitializationStrategy;

    public ClassicLogicInitializationStrategy(long seed) {
        this.randomLogicInitializationStrategy = new RandomLogicInitializationStrategy(seed);
    }

    @Override
    public LogicInitializationConfiguration initializeConfiguration(int boardSize) {
        if (boardSize < 2) {
            throw new IllegalArgumentException("A board size of " + boardSize + "is not compatible with this strategy");
        }
        LogicInitializationConfiguration configuration = randomLogicInitializationStrategy
                .initializeConfiguration(boardSize);
        while (configuration.knightPosition().equals(configuration.pawnPosition())) {
            configuration = randomLogicInitializationStrategy.initializeConfiguration(boardSize);
        }
        return configuration;
    }
}
