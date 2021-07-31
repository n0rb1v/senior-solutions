package authors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a left join fetch a.books where a.name like :prefix%")
    List<Author> findAuthorsByPrefix(@Param("prefix") String prefix);

    List<Author> findAuthorsByNameStartsWith(String prefix);

//    @Query("update Author a set a.name where a.id=:id")
//    @Modifying(clearAutomatically = true,flushAutomatically = true)
//    void updateAuthor(@Param("id") long id, @Param("name") String name);

}
