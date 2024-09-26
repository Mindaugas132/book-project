package com.mindaugas.book_project.config;

import com.mindaugas.book_project.entity.Book;
import com.mindaugas.book_project.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        var genres = new String[]{"History", "Action", "Horror", "Comedy", "Mystery", "Adventure", "Romance Novel"};

        return args -> {
            for (int i = 1; i <= 40; i++) {
                Book book = new Book(
                        "Great Book Part " + i,
                        "M&M",
                        2024 - i,
                        genres[(int) (Math.random() * genres.length)],
                        Math.round(Math.random() * 5 * 100.0) / 100.0
                );
                System.out.println("Saving book: " + book);
                repository.save(book);
            }
        };
    }
}
