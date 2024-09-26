package com.mindaugas.book_project.service;

import com.mindaugas.book_project.entity.Book;
import com.mindaugas.book_project.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book("Test OK title", "M&M", 2024, "Comedy", 1.8);
        Book book2 = new Book("Some book", "Author Unknown", 1993, "Horror", 4.3);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
        assertEquals("Test OK title", books.get(0).getTitle());
        assertEquals("Horror", books.get(1).getGenre());
    }

    @Test
    void testGetBooksByTitle() {
        Book book = new Book("Test 2 OK title", "M&M", 2024, "Comedy", 1.8);
        when(bookRepository.findByTitle("Test 2 OK title")).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.getBooksByTitle("Test 2 OK title");
        assertEquals(1, books.size());
        assertEquals("Test 2 OK title", books.get(0).getTitle());
        assertEquals("M&M", books.get(0).getAuthor());
    }

    // TODO: Write more tests
    // Although, writing testcases for your code is doubting your own code and that's a sign of weakness :))))))
}