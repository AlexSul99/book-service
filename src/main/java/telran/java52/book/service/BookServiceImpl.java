package telran.java52.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFoundException;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;
	
	
	

    @Transactional
    @Override
    public Boolean addBook(BookDto bookDto) {
        if(bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName()).orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
                .collect(Collectors.toSet());
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
        bookRepository.save(book);
        return true;
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto removeBook(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.delete(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto updateBookTitle(String isbn, String newTitle) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(newTitle);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto[] findBooksByAuthor(String author) {
        Set<Book> books = bookRepository.findByAuthorsName(author);
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).toArray(BookDto[]::new);
    }

    @Override
    public BookDto[] findBooksByPublisher(String publisher) {
        Set<Book> books = bookRepository.findByPublisherPublisherName(publisher);
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).toArray(BookDto[]::new);
    }

    @Override
    public AuthorDto[] findBookAuthors(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return book.getAuthors().stream().map(author -> modelMapper.map(author, AuthorDto.class))
                .toArray(AuthorDto[]::new);
    }

    @Override
    public String[] findPublishersByAuthor(String author) {
        Set<String> publishers = bookRepository.findPublishersByAuthorName(author);
        return publishers.toArray(new String[0]);
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String author) {
        Author authorEntity = authorRepository.findById(author).orElseThrow(EntityNotFoundException::new);
        Set<Book> books = bookRepository.findByAuthorsName(author);
        for (Book book : books) {
            book.getAuthors().remove(authorEntity);
        }
        bookRepository.saveAll(books);          
        authorRepository.delete(authorEntity);    
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

}
