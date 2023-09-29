package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {

    Optional<Goals> findByGoalsIdAndDeletedAtIsNull(Integer id);
    Boolean existsByDescriptionAndDeletedAtIsNull(String description);
}
