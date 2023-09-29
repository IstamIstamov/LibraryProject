package com.company.LibraryProject.controller;
import com.company.LibraryProject.dto.BooksDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/create")
    public ResponseDto<BooksDto> create(@Valid @RequestBody BooksDto dto) {
        return bookService.createBook(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<BooksDto> get(@PathVariable("id") Integer bookId) {
        return bookService.getBook(bookId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<BooksDto> update(@RequestBody BooksDto dto,
                                        @PathVariable("id") Integer bookId) {
        return bookService.updateBook(dto, bookId);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto<BooksDto> delete(@PathVariable("id") Integer bookId) {
        return bookService.deleteBook(bookId);
    }
    @GetMapping("/get-all")
    public ResponseDto<List<BooksDto>> getAll() {
        return bookService.getAllBook();
    }
}