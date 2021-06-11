import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private LocationParser lp;

    @BeforeEach
    void beforeEach() {
        lp = new LocationParser();
    }

    @Test
    void testParse() {
        Location temp = lp.parse("Budapest,47.497912,19.040235");
        assertEquals(new Location("Budapest",47.497912,19.040235),temp);
    }

    @Test
    void testEquator() {
        Location temp = lp.parse("Budapest,0,19.040235");
        assertEquals(true,temp.isOnEquator());
    }

    @Test
    void testPrimeMeridian() {
        Location temp = lp.parse("Budapest,47.497912,0");
        assertEquals(true,temp.isOnPrimeMeridian());
    }
}