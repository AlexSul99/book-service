package telran.java52.book.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    final BookService bookService;

    @PostMapping("/book")
    public Boolean addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @PutMapping("/book/{isbn}/title/{title}")
    public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String title) {
        return bookService.updateBookTitle(isbn, title);
    }

    @GetMapping("/books/author/{author}")
    public BookDto[] findBooksByAuthor(@PathVariable String author) {
        return bookService.findBooksByAuthor(author);
    }

    @GetMapping("/books/publisher/{publisher}")
    public BookDto[] findBooksByPublisher(@PathVariable String publisher) {
        return bookService.findBooksByPublisher(publisher);
    }

    @GetMapping("/publishers/author/{author}")
    public String[] findPublishersByAuthor(@PathVariable String author) {
        return bookService.findPublishersByAuthor(author);
    }
    
    @DeleteMapping("/authors/{author}")
    public AuthorDto removeAuthor(@PathVariable String author) {
        return bookService.removeAuthor(author);
    }
}
