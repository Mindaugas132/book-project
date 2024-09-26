package com.mindaugas.book_project.controller;

import com.mindaugas.book_project.entity.Book;
import com.mindaugas.book_project.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book("Test OK title", "M&M", 2024, "Comedy", 1.8);
        Book book2 = new Book("Some book", "Author Unknown", 1993, "Horror", 4.3);
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test OK title"))
                .andExpect(jsonPath("$[1].title").value("Some book"))
                .andExpect(jsonPath("$[0].year").value(2024));
    }

    @Test
    void testRateBook() throws Exception {
        Book book = new Book("Test 3", "M&M", 2014, "Comedy", 4.0);
        book.setId(1L);
        when(bookService.findById(1L)).thenReturn(Optional.of(book));
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1/rate").param("rating", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4.5));

        mockMvc.perform(put("/api/books/1/rate").param("rating", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4.0));
    }

    // TODO: Write more tests
    // Although, when writing testcases you are doubting your own code and doubting your code is a sign of weakness :))))))
}
