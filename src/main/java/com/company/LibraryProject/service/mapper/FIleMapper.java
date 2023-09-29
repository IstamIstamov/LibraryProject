package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.FileModelDto;
import com.company.LibraryProject.model.FileModel;
import org.springframework.stereotype.Component;

@Component
public class FIleMapper {
    public FileModelDto toDto(FileModel fileModel) {
        return FileModelDto.builder()
                .fileId(fileModel.getFileId())
                .fileName(fileModel.getFileName())
                .filePath(fileModel.getFilePath())
                .ext(fileModel.getExt())
                .createdAt(fileModel.getCreatedAt())
                .updatedAt(fileModel.getUpdatedAt())
                .deletedAt(fileModel.getDeletedAt())
                .build();
    }
}
