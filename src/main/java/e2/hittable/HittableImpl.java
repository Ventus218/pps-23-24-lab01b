package e2.hittable;

public class HittableImpl implements Hittable {

    private boolean wasHit = false;

    @Override
    public boolean wasHit() {
        return wasHit;
    }

    @Override
    public void hit() {
        if (wasHit) {
            throw new IllegalStateException("Already hit");
        }
        wasHit = true;
    }
}
