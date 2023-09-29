package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, PasswordEncoder.class})
public abstract class UserMapper {


    @Lazy
    @Autowired
    protected CardMapper cardMapper;

    @Lazy
    @Autowired
    protected OrdersMapper ordersMapper;

    @Autowired
    protected PasswordEncoder passwordEncoder;


    @Mapping(target = "authority", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserDto dto);


    //@Mapping(target = "cards", expression = "java(user.getCards().stream().map(cardMapper::toDtoNotUserId).collect(Collectors.toSet()))")
    //@Mapping(target = "orders", expression = "java(user.getOrders().stream().map(ordersMapper::toDtoNotUserId).collect(Collectors.toSet()))")
    public abstract UserDto toDto(User user);

    //@Mapping(target = "cards", ignore = true)
    //@Mapping(target = "orders", ignore = true)
    @Mapping(target = "authority", ignore = true)
    public abstract UserDto toDtoByNotCards(User user);


    @Mapping(target = "authority", ignore = true)
    //@Mapping(target = "cards", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);
}
