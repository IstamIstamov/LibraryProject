package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer id);
    boolean existsByCardNumber(Long cardNumber);

}
