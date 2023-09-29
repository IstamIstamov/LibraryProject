package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.CardDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/create")
    public ResponseDto<CardDto> createCard(@Valid @RequestBody CardDto dto) {
        return cardService.createCard(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<CardDto> getCard(@PathVariable("id") Integer cardId) {
        return cardService.getCard(cardId);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<CardDto> updateCard(@RequestBody CardDto dto,
                                           @PathVariable("id") Integer cardId) {
        return cardService.updateCard(dto, cardId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CardDto> deleteCard(@PathVariable("id") Integer cardId) {
        return cardService.deleteCard(cardId);
    }

    @GetMapping("/get-all")
    public ResponseDto<List<CardDto>> getAllCard() {
        return cardService.getAll();
    }
}
