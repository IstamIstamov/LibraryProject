package com.company.LibraryProject.service.validation;
import com.company.LibraryProject.dto.BooksDto;
import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidate {
    private final BookRepository bookRepository;
    public List<ErrorDto> validate(BooksDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (dto.getPrice()<0) {
            errors.add(new ErrorDto("price", "Price should be positive number"));
        }
        if (dto.getAmount()<0) {
            errors.add(new ErrorDto("amount", "Amount should be positive number"));
        }
        if(dto.getPage()<=0){
            errors.add(new ErrorDto("page", "Pages cannot be negative or zero"));
        }
        if (bookRepository.existsByNameAndDeletedAtIsNull(dto.getName())){
            errors.add(new ErrorDto("name", "This is book name already exist!"));
        }
        return errors;
    }
}
