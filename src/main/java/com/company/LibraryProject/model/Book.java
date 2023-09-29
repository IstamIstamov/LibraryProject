package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = ("books"))
public class Book {
    @Id
    @GeneratedValue(generator = "book_seq_id")
    @SequenceGenerator(name = "book_seq_id",sequenceName = "book_seq_id",allocationSize = 1)
    @Column(name = ("books_id"))
    private Integer bookId;
    private String name;
    private Double price;
    private Integer page;
    private Integer amount;

    private Integer ordersBookId;
    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private Set<Authors> authors;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<FileModel> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("books_id"), referencedColumnName = ("books_id"))
    private Goals goals;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("books_id"), referencedColumnName = ("books_id"))
    private Publisher publisher;

    private LocalDate publisherAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
