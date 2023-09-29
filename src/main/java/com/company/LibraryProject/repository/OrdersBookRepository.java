package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.OrdersBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersBookRepository extends JpaRepository<OrdersBook,Integer> {
    Optional<OrdersBook>findByOrdersBookIdAndDeletedAtIsNull(Integer ordersBookId);
}
