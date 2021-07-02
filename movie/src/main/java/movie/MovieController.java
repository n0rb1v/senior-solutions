package movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> listMovies(@RequestParam Optional<String> title) {
        return movieService.listMovies(title);
    }
    @GetMapping("/{id}")
    public MovieDTO findMovieById(@PathVariable("id") long id){
        return movieService.findMovieById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createInstrument(@Valid @RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }
    @PutMapping("/{id}/rating")
    public MovieDTO updateMovie(@PathVariable("id") long id, @Valid @RequestBody UpdateRateCommand command){
        return movieService.updateMovie(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrument(@PathVariable("id") long id){
        movieService.deleteMovie(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        movieService.deleteAll();
    }
}
