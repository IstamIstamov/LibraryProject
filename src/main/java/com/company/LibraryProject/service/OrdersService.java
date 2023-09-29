package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.OrdersDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Orders;
import com.company.LibraryProject.repository.OrdersRepository;
import com.company.LibraryProject.service.mapper.OrdersMapper;
import com.company.LibraryProject.service.validation.OrdersValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final OrdersValidate ordersValidate;

    public ResponseDto<OrdersDto> create(OrdersDto ordersDto) {
        List<ErrorDto> errors = ordersValidate.validate(ordersDto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<OrdersDto>builder()
                    .message("Validate error!")
                    .data(ordersDto)
                    .code(-2)
                    .errors(errors)
                    .build();
        }
        try {
            Orders orders = ordersMapper.toEntity(ordersDto);
            orders.setCreatedAt(LocalDateTime.now());
            ordersRepository.save(orders);
            log.info(String.format("This is orders %d id successful created!",orders.getOrdersId()));
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message(String.format("This is orders %d id successful created!",orders.getOrdersId()))
                    .data(ordersMapper.toDtoByNotOrdersBook(orders))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Orders while saving error :: %s", e.getMessage()));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("Orders while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<OrdersDto> get(Integer ordersId) {
        Optional<Orders> optional = ordersRepository.findByOrdersIdAndDeletedAtIsNull(ordersId);
        if (optional.isEmpty()) {
            log.warn(String.format("This is orders %d id not found!",ordersId));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("This is orders %d id not found!",ordersId))
                    .code(-1)
                    .build();
        }
        log.info("Ok");
        return ResponseDto.<OrdersDto>builder()
                .success(true)
                .message("OK")
                .data(ordersMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<OrdersDto> update(Integer ordersId, OrdersDto ordersDto) {
        Optional<Orders> optional = ordersRepository.findByOrdersIdAndDeletedAtIsNull(ordersId);
        if (optional.isEmpty()) {
            log.warn(String.format("This is orders %d id not found!",ordersId));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("This is orders %d id not found!",ordersId))
                    .code(-1)
                    .build();
        }
        List<ErrorDto> errors = ordersValidate.validate(ordersDto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<OrdersDto>builder()
                    .message("Validate error!")
                    .data(ordersDto)
                    .code(-2)
                    .errors(errors)
                    .build();
        }
        try {
            Orders orders = optional.get();
            orders.setUpdatedAt(LocalDateTime.now());
            ordersMapper.update(orders,ordersDto);
            ordersRepository.save(orders);
            log.info(String.format("This is orders %d id successful updated!",orders.getOrdersId()));
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message(String.format("This is orders %d id successful updated!",orders.getOrdersId()))
                    .data(ordersMapper.toDtoByNotOrdersBook(orders))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Orders while saving error :: %s", e.getMessage()));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("Orders while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<OrdersDto> delete(Integer ordersId) {
        Optional<Orders> optional = ordersRepository.findByOrdersIdAndDeletedAtIsNull(ordersId);
        if (optional.isEmpty()) {
            log.warn(String.format("This is orders %d id not found!",ordersId));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("This is orders %d id not found!",ordersId))
                    .code(-1)
                    .build();
        }
        try {
            Orders orders = optional.get();
            orders.setDeletedAt(LocalDateTime.now());
            ordersRepository.save(orders);
            log.info(String.format("This is orders %d id successful updated!",orders.getOrdersId()));
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message(String.format("This is orders %d id successful deleted!",orders.getOrdersId()))
                    .data(ordersMapper.toDtoByNotOrdersBook(orders))
                    .build();
        } catch (Exception e) {
            log.error(String.format("Orders while saving error :: %s", e.getMessage()));
            return ResponseDto.<OrdersDto>builder()
                    .message(String.format("Orders while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<List<OrdersDto>> getAll() {
        return ResponseDto.<List<OrdersDto>>builder()
                .message("Ok")
                .success(true)
                .code(0)
                .data(ordersRepository.findAll().stream().map(ordersMapper::toDto).toList())
                .build();
    }
}
