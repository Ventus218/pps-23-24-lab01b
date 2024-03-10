package e2.grid;

import e2.Pair;

public class XYAddressableImpl implements XYAddressable {

    private final Pair<Integer, Integer> coordinates;

    public XYAddressableImpl(int x, int y) {
        this.coordinates = new Pair<>(x, y);
    }

    @Override
    public Integer getX() {
        return coordinates.getX();
    }

    @Override
    public Integer getY() {
        return coordinates.getY();
    }

}
