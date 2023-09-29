package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.CardDto;
import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Card;
import com.company.LibraryProject.repository.CardRepository;
import com.company.LibraryProject.service.mapper.CardMapper;
import com.company.LibraryProject.service.validation.CardValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardMapper cardMapper;

    private final CardRepository cardRepository;

    private final CardValidate cardValidate;

    public ResponseDto<CardDto> createCard(CardDto dto) {

        List<ErrorDto> errors = cardValidate.validate(dto);
        if (!errors.isEmpty()){
            log.warn("Validation error!");
            return ResponseDto.<CardDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }
        try {
            Card card = cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            cardRepository.save(card);
            log.info(String.format("This is card %d id successful created!",card.getCardId()));
            return ResponseDto.<CardDto>builder()
                    .message(String.format("This is card %d id successful created!",card.getCardId()))
                    .success(true)
                    .data(cardMapper.toDtoNotUserId(card))
                    .build();
        } catch (Exception c) {
            log.error("Card while saving error: " + c.getMessage());
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .message("Card while saving error: " + c.getMessage())
                    .build();

        }
    }
    public ResponseDto<CardDto> getCard(Integer cardId) {
        try {
            Optional<Card> optional = cardRepository.findByCardIdAndDeletedAtIsNull(cardId);
            if (optional.isEmpty()) {
                log.warn("Card is not found!");
                return ResponseDto.<CardDto>builder()
                        .message("Card is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<CardDto>builder()
                    .message("OK")
                    .success(true)
                    .data(cardMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            log.error("Database Error: " + c.getMessage());
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }
    public ResponseDto<CardDto> updateCard(CardDto dto, Integer id) {
        List<ErrorDto> errors = cardValidate.validate(dto);
        if (!errors.isEmpty()){
            log.warn("Validation error!");
            return ResponseDto.<CardDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }

        Optional<Card> optional = cardRepository.findByCardIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn("Card is not found!");
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }

        try {
            Card card = optional.get();
            card.setUpdatedAt(LocalDateTime.now());
            cardMapper.update(card,dto);
            cardRepository.save(card);
            log.info(String.format("This is card %d id successful updated!",card.getCardId()));
            return ResponseDto.<CardDto>builder()
                    .message(String.format("This is card %d id successful updated!",card.getCardId()))
                    .success(true)
                    .data(cardMapper.toDtoNotUserId(card))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Card while saving error :: %s",e.getMessage()));
            return ResponseDto.<CardDto>builder()
                    .message("Card while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CardDto> deleteCard(Integer id) {
        Optional<Card> optional = cardRepository.findByCardIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn("Card is not found!");
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }
        try {
            Card card = optional.get();
            card.setDeletedAt(LocalDateTime.now());
            cardRepository.save(card);
            log.info(String.format("This is card %d id successful deleted!",id));
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message(String.format("This is card %d id successful deleted!",id))
                    .data(cardMapper.toDtoNotUserId(card))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Card while saving error :: %s",e.getMessage()));
            return ResponseDto.<CardDto>builder()
                    .message("Card while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<List<CardDto>> getAll() {
        log.info("OK");
        return ResponseDto.<List<CardDto>>builder()
                .message("OK")
                .code(0)
                .success(true)
                .data(cardRepository.findAll().stream().map(cardMapper::toDtoNotUserId).toList())
                .build();
    }
}