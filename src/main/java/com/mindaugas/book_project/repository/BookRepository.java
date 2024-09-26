package com.mindaugas.book_project.repository;

import com.mindaugas.book_project.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByYear(int year);
    List<Book> findByRating(double rating);
}
