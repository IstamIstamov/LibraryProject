package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("publishers"))
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1,sequenceName = "publisher_seq_id", name = "publisher_seq_id")
    @Column(name = ("publisher_id"))
    private Integer publisherId;
    private String publisher;
    private String location;
    @Column(name = ("books_id"))
    private Integer bookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
