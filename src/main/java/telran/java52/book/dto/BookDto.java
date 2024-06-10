package telran.java52.book.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class BookDto {
	String isbn;
	String title;
	Set<AuthorDto> authors;
    String publisher;
	
}
