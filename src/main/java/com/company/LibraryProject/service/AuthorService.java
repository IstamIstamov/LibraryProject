package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.AuthorDto;
import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Authors;
import com.company.LibraryProject.repository.AuthorRepository;
import com.company.LibraryProject.service.mapper.AuthorMapper;
import com.company.LibraryProject.service.validation.AuthorValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorValidate authorValidate;

    public ResponseDto<AuthorDto> create(AuthorDto dto) {
        List<ErrorDto> errors = this.authorValidate.validate(dto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<AuthorDto>builder()
                    .message("Validate error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }
        try {
            Authors author = authorMapper.toEntity(dto);
            author.setCreatedAt(LocalDateTime.now());
            this.authorRepository.save(author);
            log.info(String.format("This is author %d di successful created!", author.getAuthorId()));

            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d di successful created!", author.getAuthorId()))
                    .code(0)
                    .success(true)
                    .data(authorMapper.toDtoByNotBook(author))
                    .build();

        } catch (Exception e) {
            log.error(String.format("While saving error %s", e.getMessage()));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("While saving error %s", e.getMessage()))
                    .code(-3)
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<AuthorDto> get(Integer authorId) {
        Optional<Authors> optional = authorRepository.findAllByAuthorIdAndDeletedAtIsNull(authorId);
        if (optional.isEmpty()) {
            log.info(String.format("This is author %d id not found!", authorId));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d id not found!", authorId))
                    .code(-1)
                    .build();
        }
        log.info("OK");
        return ResponseDto.<AuthorDto>builder()
                .message("OK")
                .code(0)
                .success(true)
                .data(authorMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<AuthorDto> update(AuthorDto dto, Integer authorId) {
        Optional<Authors> optional = authorRepository.findAllByAuthorIdAndDeletedAtIsNull(authorId);
        if (optional.isEmpty()) {
            log.info(String.format("This is author %d id not found!", authorId));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d id not found!", authorId))
                    .code(-1)
                    .build();
        }
        List<ErrorDto> errors = this.authorValidate.validate(dto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<AuthorDto>builder()
                    .message("Validate error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }
        try {
            Authors author = optional.get();
            author.setUpdatedAt(LocalDateTime.now());
            authorMapper.validate(author,dto);
            authorRepository.save(author);
            log.info(String.format("This is author %d id successful updated!", author.getAuthorId()));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d id successful updated!", author.getAuthorId()))
                    .code(0)
                    .data(authorMapper.toDtoByNotBook(author))
                    .build();
        } catch (Exception e) {
            log.error(String.format("While saving error %s", e.getMessage()));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("While saving error %s", e.getMessage()))
                    .code(-3)
                    .success(false)
                    .build();
        }
    }

    public ResponseDto<AuthorDto> delete(Integer authorId) {
        Optional<Authors> optional = authorRepository.findAllByAuthorIdAndDeletedAtIsNull(authorId);
        if (optional.isEmpty()) {
            log.info(String.format("This is author %d id not found!", authorId));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d id not found!", authorId))
                    .code(-1)
                    .build();
        }
        try {
            Authors author = optional.get();
            author.setDeletedAt(LocalDateTime.now());
            authorRepository.save(author);
            log.info(String.format("This is author %d id successful deleted!", author.getAuthorId()));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("This is author %d id successful deleted!", author.getAuthorId()))
                    .code(0)
                    .data(authorMapper.toDtoByNotBook(author))
                    .build();
        } catch (Exception e) {
            log.error(String.format("While saving error %s", e.getMessage()));
            return ResponseDto.<AuthorDto>builder()
                    .message(String.format("While saving error %s", e.getMessage()))
                    .code(-3)
                    .success(false)
                    .build();
        }

    }

    public ResponseDto<List<AuthorDto>> getAll() {
        log.info("OK");
        return ResponseDto.<List<AuthorDto>>builder()
                .message("OK")
                .success(true)
                .code(0)
                .data(this.authorRepository.findAll().stream().map(authorMapper::toDto).toList())
                .build();
    }
}
