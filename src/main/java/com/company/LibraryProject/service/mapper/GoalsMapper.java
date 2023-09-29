package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.GoalsDto;
import com.company.LibraryProject.model.Goals;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public  interface GoalsMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
     Goals toEntity(GoalsDto goalsDto);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
     GoalsDto toDto(Goals goals);
    @Mapping(target = "bookId",ignore = true)
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
     GoalsDto toDtoNotBookId(Goals goals);
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Goals goals,GoalsDto goalsDto);
}
