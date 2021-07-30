package authors;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
    private AuthorService authorService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@Valid @RequestBody CreateAuthorCommand command) {
        return authorService.createAuthor(command);
    }

    @GetMapping
    public List<AuthorDTO> listAuthors(@RequestParam Optional<String> prefix) {
        return authorService.listAuthors(prefix);
    }
    @PostMapping("/{id}/books")
    public AuthorDTO addBook(@PathVariable("id") long id, @Valid @RequestBody CreateBookCommand command) {
        return authorService.addBook(command, id);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteAuthor(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
    }
}
