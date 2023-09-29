package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.PublisherDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @PostMapping("/create")
    public ResponseDto<PublisherDto> create(@Valid @RequestBody PublisherDto dto){
        return publisherService.createPublisher(dto);
    }
    @GetMapping("/get/{id}")
    public ResponseDto<PublisherDto> get(@PathVariable Integer id){
        return publisherService.getPublisher(id);
    }
    @PutMapping("/update/{id}")
    public ResponseDto<PublisherDto> update(@RequestBody PublisherDto dto, @PathVariable Integer id){
        return publisherService.updatePublisher(dto, id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto<PublisherDto> delete(@PathVariable Integer id){
        return publisherService.deletePublisher(id);
    }
    @GetMapping("/get-all")
    public ResponseDto<List<PublisherDto>> getAll(){
        return publisherService.getAllPublisher();
    }
}
