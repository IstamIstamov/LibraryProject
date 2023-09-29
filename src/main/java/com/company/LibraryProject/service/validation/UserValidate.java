package com.company.LibraryProject.service.validation;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    public List<ErrorDto> validate(UserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (userRepository.existsByEmail(dto.getEmail())) {
            errors.add(new ErrorDto("email", "Email is already exist"));
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            errors.add(new ErrorDto("phoneNumber", "Phone number is already exist."));
        }
        return errors;
    }
}
