package com.mindaugas.book_project.controller;

import com.mindaugas.book_project.entity.Book;
import com.mindaugas.book_project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/filter")
    public List<Book> filterBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) Integer year,
                                  @RequestParam(required = false) String genre,
                                  @RequestParam(required = false) Double rating) {
        if (title != null) {
            return bookService.getBooksByTitle(title);
        } else if (author != null) {
            return bookService.getBooksByAuthor(author);
        } else if (year != null) {
            return bookService.getBooksByYear(year);
        } else if (genre != null) {
            return bookService.getBooksByGenre(genre);
        } else if (rating != null) {
            return bookService.getBooksByRating(rating);
        } else {
            return bookService.getAllBooks();
        }
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}/rate")
    public Book rateBook(@PathVariable Long id, @RequestParam double rating) {

        Optional<Book> optionalBook = bookService.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            List<Double> newAllRatings = book.getAllRatings();
            newAllRatings.add(rating);
            book.setAllRatings(newAllRatings);
            double sumOfRatings = newAllRatings.stream()
                    .mapToDouble(Double::doubleValue).sum();
            book.setRating(sumOfRatings / newAllRatings.size());
            return bookService.saveBook(book);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        // To rate without front part use Postman or cmd line e.g.: curl -X PUT "http://localhost:8080/api/books/1/rate?rating=4.5"
    }
}