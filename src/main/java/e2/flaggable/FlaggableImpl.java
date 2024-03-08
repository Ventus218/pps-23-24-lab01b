package e2.flaggable;

public class FlaggableImpl implements Flaggable {

    private boolean hasFlag = false;

    @Override
    public boolean hasFlag() {
        return hasFlag;
    }

    @Override
    public void setFlag(boolean flag) {
        hasFlag = flag;
    }
}
