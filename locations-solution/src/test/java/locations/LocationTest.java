package locations;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assumptions.assumeThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @TestFactory
    Stream<DynamicTest> testDynamic() {
        return Stream.of(new String[][]{{"A,34.67,66.87", "false"}, {"B,0,66.87", "true"}, {"C,34.67,0", "false"}})
                .map(loc -> DynamicTest.dynamicTest("is on Equator: " + loc[1] + ": " + loc[0],
                        () -> assertEquals(Boolean.parseBoolean(loc[1]), lp.parse(loc[0]).isOnEquator())
                ));
    }

    @TempDir
    Path tempDir;

    @Test
    void testWriteLocation() throws IOException {
        Path file = tempDir.resolve("location.csv");
        List<Location> locationList = List.of(
                new Location("A", 34.67, 66.87),
                new Location("B", 0, 66.87),
                new Location("C", 34.67, 0));
        new LocationService().writeLocations(file, locationList);

        assertEquals("A,34.67,66.87\n" +
                "B,0.0,66.87\n" +
                "C,34.67,0.0\n", Files.readString(file));
    }

    @Test
    void hamcrestTest() {
        List<Location> result = new LocationService().readLocation(Path.of("src/test/resources/locations.csv"));
        assertThat(result, contains( //hasItem
                allOf(hasProperty("name", is("A")),
                        hasProperty("lat", is(34.67)),
                        hasProperty("lon", is(66.87))),
                allOf(hasProperty("name", is("B")),
                        hasProperty("lat", is(0.0)),
                        hasProperty("lon", is(66.87))),
                allOf(hasProperty("name", is("C")),
                        hasProperty("lat", is(34.67)),
                        hasProperty("lon", is(0.0)))
        ));
    }

    @Test
    void assertjTest() {
        List<Location> result = new LocationService().readLocation(Path.of("src/test/resources/locations.csv"));
        assertThat(result)
                .hasSize(3)
                .extracting(Location::getName, Location::getLat, Location::getLon)
                .contains(tuple("A", 34.67, 66.87),
                        tuple("B", 0.0, 66.87),
                        tuple("C", 34.67, 0.0));
    }

    @Test
    void conditionTest() {
        List<Location> result = new LocationService().readLocation(Path.of("src/test/resources/locations.csv"));
        Condition<Location> zeroParam =
                new Condition<>(l -> l.getLon() == 0 || l.getLat() == 0, "has zero param");
        assertThat(result).areExactly(2,zeroParam);

    }

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    DistanceService distanceService;
    @Test
    void testMock() {
        when(locationRepository.findByName("A")).thenReturn(Optional.of(new Location("A", 47.497912,19.040235)));
        when(locationRepository.findByName("B")).thenReturn(Optional.of(new Location("B",48.210033,16.363449)));

        assertEquals(214835.83, distanceService.calculateDistance("A","B").get(), 0.01);
        assertEquals(true, distanceService.calculateDistance("B","C").isEmpty());
        verify(locationRepository).findByName("A");
        verify(locationRepository,times(2)).findByName("B");

    }
}