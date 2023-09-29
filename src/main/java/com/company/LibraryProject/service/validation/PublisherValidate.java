package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.PublisherDto;
import com.company.LibraryProject.repository.PublisherRepository;
import com.company.LibraryProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherValidate {
    private final PublisherRepository publisherRepository;
    private final BookService bookService;
    public List<ErrorDto> validate(PublisherDto publisherDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (bookService.getBook(publisherDto.getBookId()).getData() == null){
            errors.add(new ErrorDto("bookId", "Book is not found!"));
        }
        if (publisherRepository.existsByLocationAndDeletedAtIsNull(publisherDto.getLocation())){
            errors.add(new ErrorDto("location", "This is location already exist!"));
        }
        return errors;
    }
}
