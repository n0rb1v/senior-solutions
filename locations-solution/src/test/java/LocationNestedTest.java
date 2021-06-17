import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocationNestedTest {

    private LocationParser lp;

    @Nested
    class WithZero {

        @BeforeEach
        void init() {
            lp = new LocationParser();
        }

        @Test
        void testLocation() {
            Location temp = lp.parse("Test,0,0");
            assertAll(
                    () -> assertTrue(temp.isOnEquator()),
                    () -> assertTrue(temp.isOnPrimeMeridian())
            );
        }
    }

    @Nested
    class WithBudapest {

        @BeforeEach
        void init() {
            lp = new LocationParser();
        }

        @Test
        void testLocation() {
            Location temp = lp.parse("Budapest,47.497912,19.040235");
            assertAll(
                    () -> assertFalse(temp.isOnEquator()),
                    () -> assertFalse(temp.isOnPrimeMeridian())
            );
        }
    }

}
