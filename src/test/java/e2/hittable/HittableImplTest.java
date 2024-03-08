package e2.hittable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HittableImplTest {

    Hittable hittableImpl;

    @BeforeEach
    void init() {
        hittableImpl = new HittableImpl();
    }

    @Test
    void isInitiallyNotHit() {
        assertFalse(hittableImpl.wasHit());
    }

    @Test
    void canBeHit() {
        hittableImpl.hit();
        assertTrue(hittableImpl.wasHit());
    }

    @Test
    void cannotBeHitIfAlreadyIs() {
        hittableImpl.hit();
        assertThrows(IllegalStateException.class, () -> hittableImpl.hit());
    }
}
