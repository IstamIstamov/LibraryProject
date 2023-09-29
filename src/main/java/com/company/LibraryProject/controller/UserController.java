package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.*;
import com.company.LibraryProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//localhost:8080/user/crete
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@PreAuthorize(value = "hasAuthority('USER') or hasAuthority('USTA') or hasAnyAuthority('DEHQON')")
//@Secured(value = {"ADMIN", "CASHIER", "DIRECTOR"})
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseDto<UserDto> createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    //@Secured(value = {"ADMIN"})

    //@PreAuthorize(value = "hasAnyAuthority({'ADMIN', 'USER'})")
    public ResponseDto<UserDto> getUser(@PathVariable("id") Integer userId) {
        return userService.getUser(userId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<UserDto> updateUser(@PathVariable("id") Integer userId,
                                           @RequestBody UserDto dto) {
        return userService.updateUser(dto, userId);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<UserDto> deleteUser(@PathVariable("id") Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping(value = ("/get-all"))
    public ResponseDto<List<UserDto>> getAllUser() {
        return userService.getAll();
    }

    @PostMapping(value = "/sign-in")
    public ResponseDto<TokenResponseDto> signIn(@RequestBody LoginDto dto) {
        return this.userService.signIn(dto);
    }

    @PostMapping(value = "/refresh")
    public ResponseDto<TokenResponseDto> refreshToken(@RequestBody RefreshTokenDto dto) {
        return this.userService.refreshToken(dto.getRefreshToken());
    }

}
