import locations.Location;
import locations.LocationOperators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    private LocationOperators lop;
    private List<Location> temp = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        lop = new LocationOperators();
        temp.add(new Location("Budapest", 47.497912, 19.040235));
        temp.add(new Location("Cudapest", -1, 19.040235));
        temp.add(new Location("Dudapest", 47.497912, 19.040235));
        temp.add(new Location("Fudapest", 0, 19.040235));

    }

    @Test
    void filterOnNorth() {
        temp = lop.filterOnNorth(temp);
        assertAll(
                () -> assertEquals(2, temp.size()),
                () -> assertEquals("Budapest", temp.get(0).getName()),
                () -> assertEquals("Dudapest", temp.get(1).getName())
        );
    }
}