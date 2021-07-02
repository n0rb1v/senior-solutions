package movie;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private ModelMapper modelMapper;
    private AtomicLong idgen = new AtomicLong();
    private List<Movie> movies = new ArrayList<>();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> listMovies(Optional<String> title) {
        Type trgListType = new TypeToken<List<MovieDTO>>() {
        }.getType();
        List<Movie> filter = movies.stream()
                .filter(m -> title.isEmpty() || m.getTitle().contains(title.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filter, trgListType);
    }

    public MovieDTO findMovieById(long id) {
        return modelMapper.map(movies.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id)), MovieDTO.class);
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(
                idgen.incrementAndGet(),command.getTitle(), command.getLength(),new ArrayList<>(),0.0);
        movies.add(movie);
        return modelMapper.map(movie,MovieDTO.class);
    }

    public MovieDTO updateMovie(long id, UpdateRateCommand command) {
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
        movie.getRates().add(command.getRate());
        movie.setRate(movie.getRates().stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0));
        return modelMapper.map(movie, MovieDTO.class);
    }

    public void deleteMovie(long id) {
        Movie movie = movies.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
        movies.remove(movie);
    }

    public void deleteAll() {
        idgen = new AtomicLong();
        movies.clear();
    }
}
