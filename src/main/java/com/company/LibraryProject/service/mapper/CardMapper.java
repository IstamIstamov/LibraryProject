package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.CardDto;
import com.company.LibraryProject.model.Card;
import com.company.LibraryProject.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class CardMapper {
    @Lazy
    @Autowired
    protected UserMapper userMapper;
    @Lazy
    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Card toEntity(CardDto dto);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userDto", expression = "java(userMapper.toDtoByNotCards(userRepository.findByUserIdAndDeletedAtIsNull(card.getUserId()).get()))")
    @Mapping(target = "userId", ignore = true)
    public abstract CardDto toDto(Card card);

    @Mapping(target = "userDto", ignore = true)
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    public abstract CardDto toDtoNotUserId(Card card);
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Card card,CardDto dto);
}
