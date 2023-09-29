package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("authors"))
public class Authors {
    @Id
    @Column(name = ("author_id"))
    @GeneratedValue(generator = "author_seq_id")
    @SequenceGenerator(name = "author_seq_id",sequenceName = "author_seq_id",allocationSize = 1)
    private Integer authorId;
    private String firstname;
    private String lastname;
    private Integer age;
    private Integer bookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
