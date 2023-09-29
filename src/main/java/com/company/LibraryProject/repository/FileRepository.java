package com.company.LibraryProject.repository;


import com.company.LibraryProject.model.FileModel;
import com.company.LibraryProject.service.mapper.FIleMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Integer> {
    Optional<FileModel> findByFileIdAndDeletedAtIsNull(Integer fileId);
}
