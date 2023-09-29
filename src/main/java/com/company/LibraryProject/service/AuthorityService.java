package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.AuthorityDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Authorities;
import com.company.LibraryProject.repository.AuthorityRepository;
import com.company.LibraryProject.service.mapper.AuthorMapper;
import com.company.LibraryProject.service.mapper.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityMapper authorityMapper;
    private final AuthorityRepository authorityRepository;

    public ResponseDto<AuthorityDto> createAuth(AuthorityDto dto) {
        try {
            Authorities authorities = this.authorityMapper.toEntity(dto);
            authorities.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<AuthorityDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.authorityMapper.toDto(authorities))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<AuthorityDto>builder()
                    .message(String.format("Authorities while saving error message ::: %s", e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<AuthorityDto> getAuth(Integer authId) {
        return this.authorityRepository.findByIdAndDeletedAtIsNull(authId)
                .map(auth -> ResponseDto.<AuthorityDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.authorityMapper.toDto(auth))
                        .build())
                .orElse(ResponseDto.<AuthorityDto>builder()
                        .message(String.format("Authority with %d:id is not found!", authId))
                        .code(-1)
                        .build());
    }

    public ResponseDto<AuthorityDto> updateAuth(AuthorityDto dto, Integer authId) {
        return this.authorityRepository.findByIdAndDeletedAtIsNull(authId)
                .map(auth -> {
                    this.authorityMapper.updateFromDto(dto, auth);
                    this.authorityRepository.save(auth);
                    return ResponseDto.<AuthorityDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.authorityMapper.toDto(auth))
                            .build();
                }).orElse(ResponseDto.<AuthorityDto>builder()
                        .message(String.format("Authority with %d:id is not found!", authId))
                        .code(-1)
                        .build());
    }

    public ResponseDto<AuthorityDto> deleteAuth(Integer authId) {
        return this.authorityRepository.findByIdAndDeletedAtIsNull(authId)
                .map(auth -> {
                    auth.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<AuthorityDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.authorityMapper.toDto(auth))
                            .build();
                })
                .orElse(ResponseDto.<AuthorityDto>builder()
                        .message(String.format("Authority with %d:id is not found!", authId))
                        .code(-1)
                        .build());
    }
}
