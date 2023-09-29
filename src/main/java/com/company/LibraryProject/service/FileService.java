package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.FileModelDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.FileModel;
import com.company.LibraryProject.repository.FileRepository;
import com.company.LibraryProject.service.mapper.FIleMapper;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public record FileService(
        FileRepository fileRepository,
        FIleMapper fIleMapper) {
    public ResponseDto<FileModelDto> upload(MultipartFile file) {
        try {
            return ResponseDto.<FileModelDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.fIleMapper.toDto(
                            this.fileRepository.save(
                                    FileModel.builder()
                                            .fileName(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])
                                            .ext(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                            .createdAt(LocalDateTime.now())
                                            .filePath(saveFile(file))
                                            .status(true)
                                            .build())))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<FileModelDto>builder()
                    .code(-3)
                    .message("File while saving error message :: " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<FileModelDto> download(Integer fileId) {
        return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId)
                .map(fileModel -> {
                    try {
                        FileModelDto dto = this.fIleMapper.toDto(fileModel);
                        dto.setData(Files.readAllBytes(Path.of(fileModel.getFilePath())));
                        return ResponseDto.<FileModelDto>builder()
                                .success(true)
                                .message("OK")
                                .data(dto)
                                .build();
                    } catch (Exception e) {
                        return ResponseDto.<FileModelDto>builder()
                                .code(-3)
                                .message("File while getting error message :: " + e.getMessage())
                                .build();
                    }
                })
                .orElse(ResponseDto.<FileModelDto>builder()
                        .code(-1)
                        .message(String.format("File with file id :: %d is not found!", fileId))
                        .build());
    }

    public ResponseDto<FileModelDto> updateFile(MultipartFile file, Integer fileId) {
        return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId)
                .map(fileModel -> {
                    try {
                        File uFile = new File(fileModel.getFilePath());
                        if (uFile.exists()) {
                            uFile.delete();
                        }
                        return ResponseDto.<FileModelDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.fIleMapper.toDto(
                                        this.fileRepository.save(
                                                FileModel.builder()
                                                        .fileId(fileModel.getFileId())
                                                        .fileName(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])
                                                        .ext(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                                        .createdAt(LocalDateTime.now())
                                                        .filePath(saveFile(file))
                                                        .status(true)
                                                        .build())))
                                .build();
                    } catch (Exception e) {
                        return ResponseDto.<FileModelDto>builder()
                                .code(-3)
                                .message("File while updating error message :: " + e.getMessage())
                                .build();
                    }
                })
                .orElse(ResponseDto.<FileModelDto>builder()
                        .code(-1)
                        .message(String.format("File with file id :: %d is not found!", fileId))
                        .build());
    }

    public ResponseDto<FileModelDto> deleteFile(Integer fileId) {
        try {
            return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId)
                    .map(fileModel -> {
                        File file = new File(fileModel.getFilePath());
                        if (file.exists()) {
                            file.delete();
                        }
                        fileModel.setDeletedAt(LocalDateTime.now());
                        this.fileRepository.save(fileModel);
                        return ResponseDto.<FileModelDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.fIleMapper.toDto(fileModel))
                                .build();
                    })
                    .orElse(ResponseDto.<FileModelDto>builder()
                            .code(-1)
                            .message(String.format("File with file id :: %d is not found!", fileId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<FileModelDto>builder()
                    .code(-3)
                    .message("File while deleting error message :: " + e.getMessage())
                    .build();
        }
    }


    private String saveFile(MultipartFile file) throws IOException {
        String folders = String.format("%s/%s", "upload", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        File fileModel = new File(folders);
        if (!fileModel.exists()) {
            fileModel.mkdirs();
        }
        String fileName = String.format("%s/%s", folders, UUID.randomUUID());
        Files.copy(file.getInputStream(), Path.of(fileName));
        return fileName;
    }


}
