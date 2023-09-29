package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.BooksDto;
import com.company.LibraryProject.model.Book;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class BookMapper {

    @Lazy
    @Autowired
    protected AuthorMapper authorsMapper;

    @Lazy
    @Autowired
    protected GoalsMapper goalsMapper;

    @Lazy
    @Autowired
    protected PublisherMapper publisherMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Book toEntity(BooksDto dto);

    @Mapping(target = "authorsDto",
            expression = "java(book.getAuthors().stream().map(authorsMapper::toDtoNotAuthorId).collect(Collectors.toSet()))")
    @Mapping(target = "goalsDto",
            expression = "java(goalsMapper.toDtoNotBookId(book.getGoals()))")
    @Mapping(target = "publisherDto",
            expression = "java(publisherMapper.toDtoNotBookId(book.getPublisher()))")
    public abstract BooksDto toDto(Book book);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ordersBookId", ignore = true)
    @Mapping(target = "authorsDto", ignore = true)
    @Mapping(target = "imagesDto", ignore = true)
    @Mapping(target = "goalsDto", ignore = true)
    @Mapping(target = "publisherDto", ignore = true)
    public abstract BooksDto toDtoByNotOrdersBookId(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Book book, BooksDto dto);
}