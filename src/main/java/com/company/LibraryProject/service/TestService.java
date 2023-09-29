package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private UserDto userDto;

    /*public TestService(@Qualifier(value = "first") UserDto userDto) {
        this.userDto = userDto;
    }*/

    public ResponseDto<UserDto> getFirstUser() {
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(userDto)
                .build();
    }

    /*@Autowired
    public void setUserDto(@Qualifier(value = "first") UserDto userDto) {
        this.userDto = userDto;
    }*/


}
