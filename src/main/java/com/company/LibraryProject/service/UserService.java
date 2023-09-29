package com.company.LibraryProject.service;


import com.company.LibraryProject.dto.*;
import com.company.LibraryProject.model.RefreshToken;
import com.company.LibraryProject.model.User;
import com.company.LibraryProject.model.UserSession;
import com.company.LibraryProject.repository.RefreshTokenRepository;
import com.company.LibraryProject.repository.UserRepository;
import com.company.LibraryProject.repository.UserSessionRepository;
import com.company.LibraryProject.security.JwtUtil;
import com.company.LibraryProject.service.mapper.UserMapper;
import com.company.LibraryProject.service.validation.UserValidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserValidate userValidate;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSessionRepository userSessionRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public ResponseDto<UserDto> createUser(UserDto dto) {
        try {
            User user = userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            user.setEnabled(true);
            userRepository.save(user);
            log.info(String.format("Created user by id %d successfully!", user.getUserId()));
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful created!")
                    .data(userMapper.toDtoByNotCards(user))
                    .build();
        } catch (Exception e) {
            log.warn(String.format("User while saving error :: %s", e.getMessage()));
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserDto> getUser(Integer userId) {
        return this.userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .map(user -> ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.userMapper.toDto(user))
                        .build())
                .orElse(ResponseDto.<UserDto>builder()
                        .message(String.format("User with %d id is not found", userId))
                        .code(-1)
                        .build());
    }

    public ResponseDto<UserDto> updateUser(UserDto dto, Integer userId) {
        List<ErrorDto> errors = userValidate.validate(dto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<UserDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }

        try {
            return userRepository.findByUserIdAndDeletedAtIsNull(userId)
                    .map(user -> {
                        user.setUpdatedAt(LocalDateTime.now());
                        userMapper.update(user, dto);
                        userRepository.save(user);
                        log.info("User successful updated!");
                        return ResponseDto.<UserDto>builder()
                                .success(true)
                                .message("User successful updated!")
                                .data(userMapper.toDtoByNotCards(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .message("User is not found!")
                            .code(-3)
                            .build());
        } catch (Exception e) {
            log.warn(String.format("User while saving error :: %s", e.getMessage()));
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserDto> deleteUser(Integer userId) {
        try {
            return userRepository.findByUserIdAndDeletedAtIsNull(userId)
                    .map(user -> {
                        user.setDeletedAt(LocalDateTime.now());
                        user.setEnabled(false);
                        userRepository.save(user);
                        log.info("User successful deleted!");
                        return ResponseDto.<UserDto>builder()
                                .message("User successful deleted!")
                                .success(true)
                                .data(userMapper.toDtoByNotCards(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .message("User is not found!")
                            .code(-3)
                            .build());
        } catch (Exception e) {
            log.warn(String.format("User while deleting error :: %s", e.getMessage()));
            return ResponseDto.<UserDto>builder()
                    .message("User while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<List<UserDto>> getAll() {
        //log.info("Ok");
        return ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(userRepository.findAll()
                        .stream()
                        .map(userMapper::toDto)
                        .toList())
                .build();
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameAndDeletedAtIsNullAndEnabledIsTrue(username)
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with username is not found!"));
    }

    @Transactional
    public ResponseDto<TokenResponseDto> signIn(LoginDto dto) {
        UserDto userDto = loadUserByUsername(dto.getUsername());
        if (userDto == null) {
            throw new UsernameNotFoundException("User with username is not found!");
        }
        if (this.passwordEncoder.matches(dto.getPassword(), userDto.getPassword())) {
            String sessionId = UUID.randomUUID().toString();
            this.userSessionRepository.save(new UserSession(sessionId, userDto));
            String refreshTokenSessionId = UUID.randomUUID().toString();
            this.refreshTokenRepository.save(new RefreshToken(refreshTokenSessionId, userDto));
            return ResponseDto.<TokenResponseDto>builder()
                    .success(true)
                    .message("OK")
                    .data(TokenResponseDto.builder()
                            .accessToken(this.jwtUtil.generateToken(sessionId))
                            .refreshToken(refreshTokenSessionId)
                            .build())
                    .build();
        }
        throw new BadCredentialsException("Password uncorrected!");
    }

    public ResponseDto<TokenResponseDto> refreshToken(String refreshToken) {
        Optional<RefreshToken> rToken = this.refreshTokenRepository.findById(refreshToken);
        if (rToken.isEmpty()) {
            return ResponseDto.<TokenResponseDto>builder()
                    .message(String.format("This token %s is not found!", refreshToken))
                    .code(-1)
                    .build();
        }
        String sessionId = UUID.randomUUID().toString();
        this.userSessionRepository.save(new UserSession(sessionId, rToken.get().getUserDto()));

        return ResponseDto.<TokenResponseDto>builder()
                .message("OK")
                .data(TokenResponseDto.builder()
                        .accessToken(this.jwtUtil.generateToken(sessionId))
                        .refreshToken(rToken.get().getRefreshTokenId())
                        .build())
                .build();
    }

}
