package telran.java52.book.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java52.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    Set<Book> findByAuthorsName(String authorName);
    Set<Book> findByPublisherPublisherName(String publisherName);
    
    @Query("SELECT DISTINCT b.publisher.publisherName FROM Book b JOIN b.authors a WHERE a.name = :authorName")
    Set<String> findPublishersByAuthorName(@Param("authorName") String authorName);
    
   
    
}
