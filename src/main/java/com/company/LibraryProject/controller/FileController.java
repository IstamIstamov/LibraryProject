package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.FileModelDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "file")
public record FileController(
        FileService fileService
) {

    @PostMapping(value = "/upload")
    public ResponseDto<FileModelDto> upload(@RequestBody MultipartFile file) {
        return this.fileService.upload(file);
    }

    @GetMapping(value = "/download/{id}")
    public ResponseDto<FileModelDto> download(@PathVariable(value = "id") Integer fileId) {
        return this.fileService.download(fileId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<FileModelDto> updateFile(@RequestBody MultipartFile file,
                                                @PathVariable(value = "id") Integer fileId) {
        return this.fileService.updateFile(file, fileId);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<FileModelDto> deleteFile(@PathVariable(value = "id") Integer fileId) {
        return this.fileService.deleteFile(fileId);
    }

}
