package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.AuthorityDto;
import com.company.LibraryProject.model.Authorities;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AuthorityMapper {

    public abstract AuthorityDto toDto(Authorities authorities);

    @Mapping(target = "id", ignore = true)
    public abstract Authorities toEntity(AuthorityDto dto);


    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(AuthorityDto dto, @MappingTarget Authorities authorities);


}
