package telran.java52.book.service;

import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;

public interface BookService {
	Boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(String isbn);
	
	BookDto removeBook(String isbn);
	
	BookDto updateBookTitle(String isbn, String newTitle);
	
	BookDto[] findBooksByAuthor(String author);
	
	BookDto[] findBooksByPublisher(String publisher);
	
	AuthorDto[] findBookAuthors(String isbn);
	
	String[] findPublishersByAuthor(String author);
	
	AuthorDto[] removeAuthor(String author);
	
	
	
}
