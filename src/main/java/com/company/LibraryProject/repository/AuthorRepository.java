package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    Optional<Authors> findAllByAuthorIdAndDeletedAtIsNull(Integer authorId);
    Boolean existsByFirstnameAndDeletedAtIsNull(String firstName);
}
