package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.OrdersBookDto;
import com.company.LibraryProject.model.OrdersBook;
import com.company.LibraryProject.repository.OrdersRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class OrdersBookMapper {
    @Lazy
    @Autowired
    protected BookMapper bookMapper;
    @Lazy
    @Autowired
    OrdersMapper ordersMapper;
    @Lazy
    @Autowired
    protected OrdersRepository ordersRepository;
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract OrdersBook toEntity(OrdersBookDto dto) ;
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ordersId",ignore = true)
    @Mapping(target = "booksDto",expression = "java(ordersBook.getBooks().stream().map(bookMapper::toDtoByNotOrdersBookId).collect(Collectors.toSet()))")
    @Mapping(target = "ordersDto",expression = "java(ordersMapper.toDtoByNotOrdersBook(ordersRepository.findByOrdersIdAndDeletedAtIsNull(ordersBook.getOrdersBookId()).get()))")
    public abstract OrdersBookDto toDto(OrdersBook ordersBook);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ordersId",ignore = true)
    @Mapping(target = "booksDto",ignore = true)
    public abstract OrdersBookDto toDtoByNotOrdersId(OrdersBook ordersBook);
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget OrdersBook ordersBook, OrdersBookDto ordersBookDto);

}
