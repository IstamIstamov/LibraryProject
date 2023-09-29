package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.AuthorDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseDto<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto dto) {
        return authorService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<AuthorDto> getAuthor(@PathVariable("id") Integer authorId) {
        return authorService.get(authorId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<AuthorDto> updateAuthor(@RequestBody AuthorDto dto, @PathVariable("id") Integer authorId) {
        return authorService.update(dto, authorId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<AuthorDto> deleteAuthor(@PathVariable("id") Integer authorId) {
        return authorService.delete(authorId);
    }

    @GetMapping("/get-all")
    public ResponseDto<List<AuthorDto>> getAllAuthors() {
        return authorService.getAll();
    }

}
