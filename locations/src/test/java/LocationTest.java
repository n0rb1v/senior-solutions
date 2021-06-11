import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void testParse() {
        LocationParser lp = new LocationParser();
        Location temp = lp.parse("Budapest,47.497912,19.040235");
        assertEquals(new Location("Budapest",47.497912,19.040235),temp);
    }
}