package com.company.LibraryProject.controller;

import com.company.LibraryProject.dto.OrdersBookDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.service.OrdersBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ordersBook")
public class OrdersBookController {
    private final OrdersBookService ordersBookService;
    @PostMapping("/create")
    public ResponseDto<OrdersBookDto> createOrdersBook(@Valid @RequestBody OrdersBookDto ordersBookDto){
        return this.ordersBookService.create(ordersBookDto);
    }
    @GetMapping("/get/{id}")
    public ResponseDto<OrdersBookDto> getOrdersBook(@PathVariable(value = "id") Integer ordersBookId){
        return this.ordersBookService.get(ordersBookId);
    }
    @PutMapping("/update/{id}")
    public ResponseDto<OrdersBookDto> updateOrdersBook(@RequestBody OrdersBookDto ordersBookDto,@PathVariable(value = "id") Integer ordersBookId){
        return this.ordersBookService.update(ordersBookDto,ordersBookId);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto<OrdersBookDto> deleteOrdersBook(@PathVariable(value = "id") Integer ordersBookId){
        return this.ordersBookService.delete(ordersBookId);
    }
    @GetMapping("/get-all")
    public ResponseDto<List<OrdersBookDto>> getAllOrdersBook(){
        return this.ordersBookService.getAll();
    }


}
