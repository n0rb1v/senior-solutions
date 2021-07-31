package authors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
@Tag(name = "authors database")
public class AuthorController {

    private AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates an author")
    @ApiResponse(responseCode = "201", description = "author created")
    public AuthorDTO createAuthor(@Valid @RequestBody CreateAuthorCommand command) {
        return authorService.createAuthor(command);
    }

    @GetMapping
    @Operation(summary = "list the authors")
    public List<AuthorDTO> listAuthors(@RequestParam Optional<String> prefix) {
        return authorService.listAuthors(prefix);
    }
    @PostMapping("/{id}/books")
    @Operation(summary = "creates and add book to author")
    @ApiResponse(responseCode = "201", description = "book created")
    public AuthorDTO addBook(@PathVariable("id") long id, @Valid @RequestBody CreateBookCommand command) {
        return authorService.addBook(command, id);
    }
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "delete an author")
    public void deleteAuthor(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
    }
}
