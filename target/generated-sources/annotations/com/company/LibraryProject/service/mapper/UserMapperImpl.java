package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.model.Authorities;
import com.company.LibraryProject.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T23:00:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( dto.getUserId() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPhoneNumber( dto.getPhoneNumber() );
        user.setUsername( dto.getUsername() );
        user.setEnabled( dto.getEnabled() );
        user.setGender( dto.getGender() );
        user.setBirthdate( dto.getBirthdate() );
        user.setCreatedAt( dto.getCreatedAt() );
        user.setUpdatedAt( dto.getUpdatedAt() );
        user.setDeletedAt( dto.getDeletedAt() );

        user.setPassword( passwordEncoder.encode(dto.getPassword()) );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setUsername( user.getUsername() );
        userDto.setEnabled( user.getEnabled() );
        Set<Authorities> set = user.getAuthority();
        if ( set != null ) {
            userDto.setAuthority( new LinkedHashSet<Authorities>( set ) );
        }
        userDto.setGender( user.getGender() );
        userDto.setBirthdate( user.getBirthdate() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setDeletedAt( user.getDeletedAt() );

        return userDto;
    }

    @Override
    public UserDto toDtoByNotCards(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setUsername( user.getUsername() );
        userDto.setEnabled( user.getEnabled() );
        userDto.setGender( user.getGender() );
        userDto.setBirthdate( user.getBirthdate() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setDeletedAt( user.getDeletedAt() );

        return userDto;
    }

    @Override
    public void update(User user, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUserId() != null ) {
            user.setUserId( dto.getUserId() );
        }
        if ( dto.getFirstName() != null ) {
            user.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            user.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getPhoneNumber() != null ) {
            user.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getEnabled() != null ) {
            user.setEnabled( dto.getEnabled() );
        }
        if ( dto.getGender() != null ) {
            user.setGender( dto.getGender() );
        }
        if ( dto.getBirthdate() != null ) {
            user.setBirthdate( dto.getBirthdate() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            user.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            user.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
