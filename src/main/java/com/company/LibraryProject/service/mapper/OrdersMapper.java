package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.OrdersDto;
import com.company.LibraryProject.model.Orders;
import com.company.LibraryProject.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = ("spring"), imports = {Collectors.class})
public abstract class OrdersMapper {
    @Lazy
    @Autowired
    protected OrdersBookMapper ordersBookMapper;
    @Lazy
    @Autowired
    protected UserMapper userMapper;
    @Lazy
    @Autowired
    protected UserRepository userRepository;
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Orders toEntity(OrdersDto ordersDto);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userDto", expression = "java(userMapper.toDtoByNotCards(userRepository.findByUserIdAndDeletedAtIsNull(orders.getUserId()).get()))")
    @Mapping(target = "ordersBooksDto", expression = "java(orders.getOrdersBooks().stream().map(ordersBookMapper::toDtoByNotOrdersId).collect(Collectors.toSet()))")
    public abstract OrdersDto toDto(Orders orders);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ordersBooksDto", ignore = true)
    @Mapping(target = "userDto",ignore = true)
    public abstract OrdersDto toDtoByNotOrdersBook(Orders orders);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userDto", ignore = true)
    public abstract OrdersDto toDtoNotUserId(Orders orders);
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Orders orders, OrdersDto ordersDto);
}
