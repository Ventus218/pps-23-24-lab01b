package e1;

public class ClassicLogicStrategy implements LogicStrategy {

    @Override
    public boolean canMove(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> destination) {
        return false;
    }

}
