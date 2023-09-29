package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.OrdersDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ("orders"))
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping(value = ("/create"))
    public ResponseDto<OrdersDto> create(@Valid @RequestBody OrdersDto ordersDto) {
        return ordersService.create(ordersDto);
    }

    @GetMapping(value = ("/get/{id}"))
    public ResponseDto<OrdersDto> get(@PathVariable("id") Integer ordersId) {
        return ordersService.get(ordersId);
    }

    @PutMapping(value = ("/update/{id}"))
    public ResponseDto<OrdersDto> update(@PathVariable("id") Integer ordersId,
                                         @RequestBody OrdersDto ordersDto) {
        return ordersService.update(ordersId, ordersDto);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<OrdersDto> delete(@PathVariable("id") Integer ordersId) {
        return ordersService.delete(ordersId);
    }

    @GetMapping(value = ("/get-all"))
    public ResponseDto<List<OrdersDto>> getAll() {
        return ordersService.getAll();
    }

}
