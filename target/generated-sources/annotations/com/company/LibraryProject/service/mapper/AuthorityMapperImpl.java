package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.AuthorityDto;
import com.company.LibraryProject.model.Authorities;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T23:00:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class AuthorityMapperImpl extends AuthorityMapper {

    @Override
    public AuthorityDto toDto(Authorities authorities) {
        if ( authorities == null ) {
            return null;
        }

        AuthorityDto.AuthorityDtoBuilder authorityDto = AuthorityDto.builder();

        authorityDto.id( authorities.getId() );
        authorityDto.username( authorities.getUsername() );
        authorityDto.authority( authorities.getAuthority() );
        authorityDto.userId( authorities.getUserId() );

        return authorityDto.build();
    }

    @Override
    public Authorities toEntity(AuthorityDto dto) {
        if ( dto == null ) {
            return null;
        }

        Authorities authorities = new Authorities();

        authorities.setUsername( dto.getUsername() );
        authorities.setAuthority( dto.getAuthority() );
        authorities.setUserId( dto.getUserId() );

        return authorities;
    }

    @Override
    public void updateFromDto(AuthorityDto dto, Authorities authorities) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUsername() != null ) {
            authorities.setUsername( dto.getUsername() );
        }
        if ( dto.getAuthority() != null ) {
            authorities.setAuthority( dto.getAuthority() );
        }
        if ( dto.getUserId() != null ) {
            authorities.setUserId( dto.getUserId() );
        }
    }
}
