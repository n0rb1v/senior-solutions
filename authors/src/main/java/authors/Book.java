package authors;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    @ManyToOne
    private Author author;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}
