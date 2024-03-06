package e1;

import java.util.Random;

public class RandomLogicInitializationStrategy implements LogicInitializationStrategy {

    private final Random random;

    public RandomLogicInitializationStrategy(long seed) {
        random = new Random(seed);
    }

    @Override
    public LogicInitializationConfiguration initializeConfiguration(int boardSize) {

        var knightPosition = new Pair<>(this.random.nextInt(boardSize), this.random.nextInt(boardSize));
        var pawnPosition = new Pair<>(this.random.nextInt(boardSize), this.random.nextInt(boardSize));

        return new LogicInitializationConfigurationImpl(knightPosition, pawnPosition);
    }

}
