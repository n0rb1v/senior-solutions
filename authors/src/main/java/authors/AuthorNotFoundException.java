package authors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class AuthorNotFoundException extends AbstractThrowableProblem {

    public AuthorNotFoundException() {
        super(URI.create("authors/not-found"),
               "Not found",
                Status.NOT_FOUND,
                "Author not found");
    }
}
