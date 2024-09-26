package com.mindaugas.book_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private Integer year;
    @NonNull
    private String genre;
    @NonNull
    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be more than 5")
    private Double rating;
    @ElementCollection
    @CollectionTable(name = "book_ratings", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "rating")
    private List<Double> allRatings = new ArrayList<>();
    private String description;

    // Constructor to initialize allRatings
    public Book(String title, String author, Integer year, String genre, Double rating) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.allRatings.add(rating);
    }
}
