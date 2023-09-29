package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "test")
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/get")
    public ResponseDto<UserDto> firstUser(){
        return this.testService.getFirstUser();
    }


}
