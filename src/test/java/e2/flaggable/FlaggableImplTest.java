package e2.flaggable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlaggableImplTest {

    Flaggable flaggableImpl;

    @BeforeEach
    void init() {
        flaggableImpl = new FlaggableImpl();
    }

    @Test
    void flagIsInitiallyNotSet() {
        assertFalse(flaggableImpl.hasFlag());
    }

    @Test
    void setFlag() {
        flaggableImpl.setFlag(true);
        assertTrue(flaggableImpl.hasFlag());
    }
}
