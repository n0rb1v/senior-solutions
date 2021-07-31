package authors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryIT {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAuthorsByPrefix() {
        Author author1 = new Author("Tolkien");
        Author author2 = new Author("Attila");
        Author author3 = new Author("Tobi");
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        List<Author> result = authorRepository.findAuthorsByPrefix("To");
        assertThat(result)
                .extracting(Author::getName)
                .containsOnly("Tolkien","Tobi");
    }
    @Test
    void findAuthorsByPrefix2() {
        Author author1 = new Author("Tolkien");
        Author author2 = new Author("Attila");
        Author author3 = new Author("Tobi");
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        List<Author> result = authorRepository.findAuthorsByNameStartsWith("To");
        assertThat(result)
                .extracting(Author::getName)
                .containsOnly("Tolkien","Tobi");
    }
}