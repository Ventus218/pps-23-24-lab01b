package e2.grid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XYAddressableImplTest {
    private static final int X = 1;
    private static final int Y = 1;

    XYAddressable addressable;

    @BeforeEach
    void init() {
        addressable = new XYAddressableImpl(X, Y);
    }

    @Test
    void positionCorrectlyInitialized() {
        assertAll(() -> assertEquals(X, addressable.getX()),
                () -> assertEquals(Y, addressable.getY()));
    }
}
