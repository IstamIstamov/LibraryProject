package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.GoalsDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Goals;
import com.company.LibraryProject.repository.GoalsRepository;
import com.company.LibraryProject.service.mapper.GoalsMapper;
import com.company.LibraryProject.service.validation.GoalsValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalsService {

    private final GoalsMapper goalsMapper;

    private final GoalsRepository goalsRepository;

    private final GoalsValidate goalsValidate;

    public ResponseDto<GoalsDto> createGoals(GoalsDto goalsDto) {
        List<ErrorDto> errors = goalsValidate.validate(goalsDto);
        if (!errors.isEmpty()) {
            log.warn("Validation error!");
            return ResponseDto.<GoalsDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(goalsDto)
                    .errors(errors)
                    .build();
        }
        try {
            Goals goals = goalsMapper.toEntity(goalsDto);
            goals.setCreatedAt(LocalDateTime.now());
            goalsRepository.save(goals);
            log.info(String.format("This is goals %d id successful created!", goals.getGoalsId()));
            return ResponseDto.<GoalsDto>builder()
                    .message(String.format("This is goals %d id successful created!", goals.getGoalsId()))
                    .success(true)
                    .data(goalsMapper.toDtoNotBookId(goals))
                    .build();
        } catch (Exception c) {
            log.error("Goals while saving error: " + c.getMessage());
            return ResponseDto.<GoalsDto>builder()
                    .code(-3)
                    .message("Goals while saving error: " + c.getMessage())
                    .build();
        }
    }

    public ResponseDto<GoalsDto> getGoals(Integer id) {
        Optional<Goals> optional = goalsRepository.findByGoalsIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn("Goals is not found!");
            return ResponseDto.<GoalsDto>builder()
                    .message("Goals is not found!")
                    .code(-1)
                    .build();
        }
        log.info("OK");
        return ResponseDto.<GoalsDto>builder()
                .message("OK")
                .success(true)
                .data(goalsMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<GoalsDto> updateGoals(GoalsDto goalsDto, Integer id) {
        List<ErrorDto> errors = goalsValidate.validate(goalsDto);
        if (!errors.isEmpty()) {
            log.warn("Validation error!");
            return ResponseDto.<GoalsDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(goalsDto)
                    .errors(errors)
                    .build();
        }
        Optional<Goals> optional = goalsRepository.findByGoalsIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn("Goals is not found!");
            return ResponseDto.<GoalsDto>builder()
                    .code(-1)
                    .message("Goals is not found!")
                    .build();
        }
        try {
            Goals goals = optional.get();
            goals.setUpdatedAt(LocalDateTime.now());
            goalsMapper.update(goals,goalsDto);
            goalsRepository.save(goals);
            log.info(String.format("This is goals %d id successful updated!", goals.getGoalsId()));
            return ResponseDto.<GoalsDto>builder()
                    .message(String.format("This is goals %d id successful updated!", goals.getGoalsId()))
                    .success(true)
                    .data(goalsMapper.toDtoNotBookId(goals))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Goals while saving error ::%s", e.getMessage()));
            return ResponseDto.<GoalsDto>builder()
                    .message("Goals while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<GoalsDto> deleteGoals(Integer id) {
        Optional<Goals> optional = goalsRepository.findByGoalsIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn("Goals is not found!");
            return ResponseDto.<GoalsDto>builder()
                    .code(-1)
                    .message("Goals is not found!")
                    .build();
        }
        try {
            Goals goals = optional.get();
            goals.setDeletedAt(LocalDateTime.now());
            goalsRepository.save(goals);
            log.info(String.format("This is goals %d id successful deleted!", goals.getGoalsId()));
            return ResponseDto.<GoalsDto>builder()
                    .success(true)
                    .message(String.format("This is goals %d id successful deleted!", goals.getGoalsId()))
                    .data(goalsMapper.toDtoNotBookId(goals))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Goals while saving error ::%s", e.getMessage()));
            return ResponseDto.<GoalsDto>builder()
                    .message("Goals while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<List<GoalsDto>> getAll() {
        log.info("OK");
        return ResponseDto.<List<GoalsDto>>builder()
                .message("OK")
                .code(0)
                .success(true)
                .data(goalsRepository.findAll().stream().map(goalsMapper::toDto).toList())
                .build();
    }
}
