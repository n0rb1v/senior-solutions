package authors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookCommand {
    @NotBlank
    @Schema(description = "isbn number of the book",example = "1-84356-028-3")
    private String isbn;
    @NotBlank
    @Schema(description = "book title",example = "Titanic")
    private String title;
}
