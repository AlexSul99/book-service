package telran.java52.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

}
