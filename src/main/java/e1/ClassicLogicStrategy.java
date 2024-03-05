package e1;

public class ClassicLogicStrategy implements LogicStrategy {

    @Override
    public boolean canMove(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> destination) {
        int x = destination.getX() - currentPosition.getX();
        int y = destination.getY() - currentPosition.getY();
        return x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3;
    }
}
