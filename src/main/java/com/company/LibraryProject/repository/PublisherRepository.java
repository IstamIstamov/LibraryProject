package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByPublisherIdAndDeletedAtIsNull(Integer id);
    Boolean existsByLocationAndDeletedAtIsNull(String location);
}
