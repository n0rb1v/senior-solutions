package authors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repo;
    private final ModelMapper modelMapper;

    public AuthorDTO createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        repo.save(author);
        return modelMapper.map(author,AuthorDTO.class);
    }

    public List<AuthorDTO> listAuthors(Optional<String> prefix) {
        return repo.findAll().stream()
                .filter(author -> prefix.isEmpty() || author.getName().contains(prefix.get()))
                .map(author -> modelMapper.map(author,AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorDTO addBook(CreateBookCommand command,long id) {
        Author author = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        author.addBook(new Book(command.getIsbn(), command.getTitle()));

        return modelMapper.map(author,AuthorDTO.class);
    }

    public void deleteAuthor(long id) {
        repo.deleteById(id);
    }
}
