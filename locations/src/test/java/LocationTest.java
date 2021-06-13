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
        assertEquals(new Location("Budapest", 47.497912, 19.040235), temp);
    }

    @Test
    void testParseAll() {
        Location temp = lp.parse("Budapest,47.497912,19.040235");
        assertAll(
                () -> assertEquals("Budapest", temp.getName()),
                () -> assertEquals(47.49, temp.getLat(), 0.01),
                () -> assertEquals(19.04, temp.getLon(), 0.01)
        );
    }

    @Test
    void testEquator() {
        Location temp = lp.parse("Budapest,0,19.040235");
        assertEquals(true, temp.isOnEquator());
    }

    @Test
    void testPrimeMeridian() {
        Location temp = lp.parse("Budapest,47.497912,0");
        assertEquals(true, temp.isOnPrimeMeridian());
    }

    @Test
    void testDistance() {
        Location temp1 = lp.parse("Budapest,47.497912,19.040235");
        Location temp2 = lp.parse("Vienna,48.210033,16.363449");
        assertEquals(214835.83, temp1.distanceFrom(temp2), 0.01);
    }

    @Test
    void illegalParameter() {
        IllegalArgumentException iae1 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test",99,0));
        assertEquals("hibas parameter2", iae1.getMessage());
        IllegalArgumentException iae2 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test",-99,0));
        assertEquals("hibas parameter2", iae2.getMessage());
        IllegalArgumentException iae3 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test",0,188));
        assertEquals("hibas parameter3", iae3.getMessage());
        IllegalArgumentException iae4 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test",0,-188));
        assertEquals("hibas parameter3", iae4.getMessage());

    }
}