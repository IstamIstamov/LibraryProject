package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("goals"))
public class Goals {

    @Id
    @GeneratedValue(generator = "goals_seq_id")
    @SequenceGenerator(name = "goals_seq_id",sequenceName = "goals_seq_id",allocationSize = 1)
    @Column(name = ("goals_id"))
    private Integer goalsId;
    private String language;
    private String description;
    @Column(name = ("books_id"))
    private Integer bookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
