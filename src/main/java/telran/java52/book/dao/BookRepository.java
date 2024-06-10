package telran.java52.book.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import telran.java52.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    Set<Book> findByAuthorsName(String authorName);
    Set<Book> findByPublisherPublisherName(String publisherName);
    Set<String> findPublishersByAuthorsName(String authorName);
    
}
