import java.util.List;
import java.util.stream.Collectors;

public class LocationOperators {
    List<Location> filterOnNorth(List<Location> locations) {
        return locations.stream()
                .filter(lat -> lat.getLat() > 0)
                .collect(Collectors.toList());
    }
}
