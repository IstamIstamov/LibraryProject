package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.CardDto;
import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.repository.CardRepository;
import com.company.LibraryProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class CardValidate {
    
    private final UserService userService;
    private final CardRepository cardRepository;
    
    public List<ErrorDto> validate(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (userService.getUser(dto.getUserId()).getData() == null){
            errors.add(new ErrorDto("userId", "User is not found!"));
        }
        if (cardRepository.existsByCardNumber(dto.getCardNumber())){
            errors.add(new ErrorDto("cardNumber","Card number already exist."));
        }
        return errors;
    }
}
