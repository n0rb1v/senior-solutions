import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
                IllegalArgumentException.class, () -> new Location("test", 99, 0));
        assertEquals("hibas parameter2", iae1.getMessage());
        IllegalArgumentException iae2 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test", -99, 0));
        assertEquals("hibas parameter2", iae2.getMessage());
        IllegalArgumentException iae3 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test", 0, 188));
        assertEquals("hibas parameter3", iae3.getMessage());
        IllegalArgumentException iae4 = assertThrows(
                IllegalArgumentException.class, () -> new Location("test", 0, -188));
        assertEquals("hibas parameter3", iae4.getMessage());

    }

    @RepeatedTest(value = 3, name = " isOnEquator {currentRepetition}/{totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repInfo) {
        assertEquals(values[repInfo.getCurrentRepetition() - 1][1],
                ((Location) values[repInfo.getCurrentRepetition() - 1][0]).isOnEquator());
    }

    private Object[][] values = {
            {new Location("A", 34.67, 66.87), false},
            {new Location("B", 0, 66.87), true},
            {new Location("C", 34.67, 0), false}
    };

    @ParameterizedTest(name = "data: {0} - isPrimeMeridian: {1}")
    @MethodSource("createData")
    void testIsOnPrimeMeridianMethodSource(Location location, Boolean bool) {
        assertEquals(bool, location.isOnPrimeMeridian());
    }

    static Stream<Arguments> createData() {
        return Stream.of(
                Arguments.arguments(new Location("A", 34.67, 66.87), false),
                Arguments.arguments(new Location("B", 0, 66.87), false),
                Arguments.arguments(new Location("C", 34.67, 0), true)
        );
    }

    @ParameterizedTest(name = "data: {0} - isPrimeMeridian: {1}")
    @CsvFileSource(resources = "/data.csv")
    void testCSVSource(String s1, double lat1, double lon1, String s2, double lat2, double lon2, double distance) {
        Location l1 = new Location(s1, lat1, lon1);
        Location l2 = new Location(s2, lat2, lon2);
        assertEquals(distance, l1.distanceFrom(l2), 0.01);
    }
}