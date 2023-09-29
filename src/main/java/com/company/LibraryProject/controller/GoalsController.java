package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.GoalsDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.GoalsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals")
@RequiredArgsConstructor
public class GoalsController {
    private final GoalsService goalsService;
    @PostMapping("/create")
    public ResponseDto<GoalsDto> createGoals(@Valid @RequestBody GoalsDto goalsDto){
        return goalsService.createGoals(goalsDto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<GoalsDto> getGoals(@PathVariable(value = "id") Integer id){
        return goalsService.getGoals(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<GoalsDto> updateGoals(@RequestBody GoalsDto goalsDto, @PathVariable(value = "id") Integer id){
        return goalsService.updateGoals(goalsDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<GoalsDto> deleteGoals(@PathVariable(value = "id") Integer id){
        return goalsService.deleteGoals(id);
    }
    @GetMapping("/get-all")
    public ResponseDto<List<GoalsDto>> getAllGoals(){
        return goalsService.getAll();
    }
}
