package usedcar;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CarService {
    private ModelMapper modelMapper;
    private AtomicLong id = new AtomicLong();
    private List<Car> cars = new ArrayList<>();

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CarDTO> listCars(Optional<String> brand, Optional<String> carType) {
        return cars.stream()
                .filter(c -> brand.isEmpty() || c.getBrand().equalsIgnoreCase(brand.get()))
                .filter(c -> carType.isEmpty() || c.getCarType().equalsIgnoreCase(carType.get()))
                .map(i -> modelMapper.map(i,CarDTO.class))
                .collect(Collectors.toList());
    }

    public List<String> listTypes() {
        return cars.stream()
                .map(Car::getCarType)
                .distinct()
                .collect(Collectors.toList());

    }

    public CarDTO findCarById(long id) {
        return modelMapper.map(cars.stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id)), CarDTO.class);
    }

    public CarDTO createCar(CreateCarCommand command) {
        Car car = new Car(
                id.incrementAndGet(),
                command.getBrand(),
                command.getCarType(),
                command.getAge(),
                command.getState()
                );
        cars.add(car);
        return modelMapper.map(car,CarDTO.class);    }

    public CarDTO updateCar(long id, UpdateCarCommand command) {
        Car car = findCar(id);
        car.setBrand(command.getBrand());
        car.setCarType(command.getCarType());
        car.setAge(command.getAge());
        car.setState(command.getState());
        return modelMapper.map(car,CarDTO.class);
    }

    private Car findCar(long id) {
        return cars.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
    }

    public void deleteCar(long id) {
        Car car = findCar(id);
        cars.remove(car);
    }

    public void deleteAll() {
        id = new AtomicLong();
        cars.clear();
    }
}
