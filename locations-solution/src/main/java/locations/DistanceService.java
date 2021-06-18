package locations;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.mockito.Mockito.*;

public class DistanceService {
    LocationRepository locationRepository = mock(LocationRepository.class);

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> location1 = locationRepository.findByName(name1);
        Optional<Location> location2 = locationRepository.findByName(name2);
        if (location1.isPresent() && location2.isPresent()) {
            return Optional.of(location1.get().distanceFrom(location2.get()));
        }
        return Optional.empty();
    }
}
