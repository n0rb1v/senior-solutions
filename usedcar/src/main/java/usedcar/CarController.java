package usedcar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDTO> listMovies(@RequestParam Optional<String> brand,@RequestParam Optional<String> carType) {
        return carService.listCars(brand,carType);
    }

    @GetMapping("/types")
    public List<String> listTypes() {
        return carService.listTypes();
    }

    @GetMapping("/{id}")
    public CarDTO findCarById(@PathVariable("id") long id){
        return carService.findCarById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CreateCarCommand command) {
        return carService.createCar(command);
    }

    @PutMapping("/{id}/cars")
    public CarDTO updateMovie(@PathVariable("id") long id, @Valid @RequestBody UpdateCarCommand command){
        return carService.updateCar(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrument(@PathVariable("id") long id){
        carService.deleteCar(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        carService.deleteAll();
    }
}
