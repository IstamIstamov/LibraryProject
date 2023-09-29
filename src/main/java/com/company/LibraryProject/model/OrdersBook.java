package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = ("ordersBooks"))
public class OrdersBook {
    @Id
    @GeneratedValue(generator = "ordersBook_seq_id")
    @SequenceGenerator(name = "ordersBook_seq_id",sequenceName = "ordersBook_seq_id",allocationSize = 1)
    private Integer ordersBookId;
    private Integer ordersId;
    @OneToMany(mappedBy = "ordersBookId",cascade = CascadeType.ALL)
    private Set<Book>books;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
