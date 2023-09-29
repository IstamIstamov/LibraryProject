package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.OrdersBookDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.OrdersBook;
import com.company.LibraryProject.repository.OrdersBookRepository;
import com.company.LibraryProject.service.mapper.OrdersBookMapper;
import com.company.LibraryProject.service.validation.OrdersBookValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersBookService {
    private final OrdersBookRepository oderBookRepository;
    private final OrdersBookMapper orderBookMapper;
    private final OrdersBookValidate ordersBookValidate;

    public ResponseDto<OrdersBookDto> create(OrdersBookDto ordersBookDto) {
        List<ErrorDto>errors=ordersBookValidate.validate(ordersBookDto);
        if (!errors.isEmpty()){
            log.warn("Validate error!");
            return ResponseDto.<OrdersBookDto>builder()
                    .message("Validate error!")
                    .code(-2)
                    .data(ordersBookDto)
                    .errors(errors)
                    .build();
        }
        try {
            OrdersBook ordersBook=orderBookMapper.toEntity(ordersBookDto);
            ordersBook.setCreatedAt(LocalDateTime.now());
            this.oderBookRepository.save(ordersBook);
            log.info(String.format("This is ordersBook %d id successful created!",ordersBook.getOrdersBookId()));
            return ResponseDto.<OrdersBookDto>builder()
                    .success(true)
                    .code(0)
                    .message(String.format("This is ordersBook %d id successful created!",ordersBook.getOrdersBookId()))
                    .data(orderBookMapper.toDtoByNotOrdersId(ordersBook))
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrdersBookDto>builder()
                    .message("OrderBook saving error"+e.getMessage())
                    .code(-3)
                    .build();
        }

    }

    public ResponseDto<OrdersBookDto> get(Integer orderBookId) {
        try {
            Optional<OrdersBook> optional=oderBookRepository.findByOrdersBookIdAndDeletedAtIsNull(orderBookId);
            if (optional.isEmpty()){
                log.warn(String.format("This is orderBook %d id not found!",orderBookId));
                return ResponseDto.<OrdersBookDto>builder()
                        .code(-1)
                        .message(String.format("This is orderBook %d id not found!",orderBookId))
                        .build();
            }
            log.info("Ok");
            return ResponseDto.<OrdersBookDto>builder()
                    .message("Ok")
                    .code(0)
                    .success(true)
                    .data(orderBookMapper.toDto(optional.get()))
                    .build();
        }catch (Exception e){
            log.error(String.format("Database error! %s",e.getMessage()));
            return ResponseDto.<OrdersBookDto>builder()
                    .message(String.format("Database error! %s",e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<OrdersBookDto> update( OrdersBookDto ordersBookDto,Integer orderBookId) {
        List<ErrorDto>errors=ordersBookValidate.validate(ordersBookDto);
        if (!errors.isEmpty()){
            log.warn("Validate error!");
            return ResponseDto.<OrdersBookDto>builder()
                    .message("Validate error!")
                    .code(-2)
                    .data(ordersBookDto)
                    .errors(errors)
                    .build();
        }
        Optional<OrdersBook> optional=oderBookRepository.findByOrdersBookIdAndDeletedAtIsNull(orderBookId);
        if (optional.isEmpty()){
            log.warn(String.format("This is orderBook %d id not found!",orderBookId));
            return ResponseDto.<OrdersBookDto>builder()
                    .code(-1)
                    .message(String.format("This is orderBook %d id not found!",orderBookId))
                    .build();
        }
        try {
            OrdersBook ordersBook= optional.get();
            ordersBook.setUpdatedAt(LocalDateTime.now());
          orderBookMapper.update(ordersBook,ordersBookDto);
            this.oderBookRepository.save(ordersBook);
            log.info(String.format("This is ordersBook %d id successful updated!",ordersBook.getOrdersBookId()));
            return ResponseDto.<OrdersBookDto>builder()
                    .code(0)
                    .success(true)
                    .message(String.format("This is ordersBook %d id successful updated!",ordersBook.getOrdersBookId()))
                    .data(orderBookMapper.toDtoByNotOrdersId(ordersBook))
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrdersBookDto>builder()
                    .message("OrderBook while saving error")
                    .code(-3)
                    .build();
        }

    }

    public ResponseDto<OrdersBookDto> delete(Integer orderBookId) {
        Optional<OrdersBook> optional=oderBookRepository.findByOrdersBookIdAndDeletedAtIsNull(orderBookId);
        if (optional.isEmpty()){
            log.warn(String.format("This is orderBook %d id not found!",orderBookId));
            return ResponseDto.<OrdersBookDto>builder()
                    .code(-1)
                    .message(String.format("This is orderBook %d id not found!",orderBookId))
                    .build();
        }

        try {
            OrdersBook ordersBook= optional.get();
            ordersBook.setDeletedAt(LocalDateTime.now());
            this.oderBookRepository.save(ordersBook);
            log.info(String.format("This is ordersBook %d id successful deleted!",ordersBook.getOrdersBookId()));
            return ResponseDto.<OrdersBookDto>builder()
                    .success(true)
                    .code(0)
                    .message(String.format("This is ordersBook %d id successful deleted!",ordersBook.getOrdersBookId()))
                    .data(orderBookMapper.toDtoByNotOrdersId(ordersBook))
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrdersBookDto>builder()
                    .message("OrderBook while saving error")
                    .success(false)
                    .code(0)
                    .build();
        }

    }

    public ResponseDto<List<OrdersBookDto>> getAll() {
        log.info("Ok");
        return ResponseDto.<List<OrdersBookDto>>builder()
                .message("Ok")
                .code(0)
                .success(true)
                .data(oderBookRepository.findAll()
                        .stream()
                        .map(orderBookMapper::toDto)
                        .toList())
                .build();
    }
}
