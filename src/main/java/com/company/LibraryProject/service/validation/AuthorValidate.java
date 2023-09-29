package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.AuthorDto;
import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.repository.AuthorRepository;
import com.company.LibraryProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthorValidate {
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    public List<ErrorDto> validate(AuthorDto dto) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if (bookService.getBook(dto.getBookId()).getData() == null) {
            errorDtoList.add(new ErrorDto("BookId", "Book not found!"));
        }
        if (authorRepository.existsByFirstnameAndDeletedAtIsNull(dto.getFirstname())) {
            errorDtoList.add(new ErrorDto("firstName", "This is firstName already exist!"));
        }
        if (dto.getAge() < 18) {
            errorDtoList.add(new ErrorDto("Age", "Age<18"));
        }
        return errorDtoList;
    }
}
