package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.AuthorityDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "auth")
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping(value = "/create")
    public ResponseDto<AuthorityDto> createAuth(@RequestBody AuthorityDto dto) {
        return this.authorityService.createAuth(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<AuthorityDto> getAuth(@PathVariable(value = "id") Integer authId) {
        return this.authorityService.getAuth(authId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<AuthorityDto> updateAuth(@RequestBody AuthorityDto dto,
                                                @PathVariable(value = "id") Integer authId) {
        return this.authorityService.updateAuth(dto, authId);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<AuthorityDto> deleteAuth(@PathVariable(value = "id") Integer authId) {
        return this.authorityService.deleteAuth(authId);
    }


}
