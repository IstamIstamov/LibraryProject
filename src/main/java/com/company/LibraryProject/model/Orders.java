package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = ("orders"))
public class Orders {
    @Id
    @GeneratedValue(generator = "orders_seq_id")
    @SequenceGenerator(name = "orders_seq_id",sequenceName = "orders_seq_id",allocationSize = 1)
    private Integer ordersId;
    private Integer userId;
    private Integer total;
    @OneToMany(mappedBy = "ordersId", cascade = CascadeType.ALL)
    private Set<OrdersBook> ordersBooks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
