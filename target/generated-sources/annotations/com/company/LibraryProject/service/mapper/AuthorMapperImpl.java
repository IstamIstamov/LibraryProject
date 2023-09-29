package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.AuthorDto;
import com.company.LibraryProject.model.Authors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T23:00:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public Authors toEntity(AuthorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Authors authors = new Authors();

        if ( dto.getAuthorId() != null ) {
            authors.setAuthorId( Integer.parseInt( dto.getAuthorId() ) );
        }
        authors.setFirstname( dto.getFirstname() );
        authors.setLastname( dto.getLastname() );
        authors.setAge( dto.getAge() );
        authors.setBookId( dto.getBookId() );

        return authors;
    }

    @Override
    public AuthorDto toDto(Authors authors) {
        if ( authors == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        if ( authors.getCreatedAt() != null ) {
            authorDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getCreatedAt() ) );
        }
        if ( authors.getUpdatedAt() != null ) {
            authorDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getUpdatedAt() ) );
        }
        if ( authors.getDeletedAt() != null ) {
            authorDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getDeletedAt() ) );
        }
        if ( authors.getAuthorId() != null ) {
            authorDto.setAuthorId( String.valueOf( authors.getAuthorId() ) );
        }
        authorDto.setFirstname( authors.getFirstname() );
        authorDto.setLastname( authors.getLastname() );
        authorDto.setAge( authors.getAge() );
        authorDto.setBookId( authors.getBookId() );

        return authorDto;
    }

    @Override
    public AuthorDto toDtoNotAuthorId(Authors authors) {
        if ( authors == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        if ( authors.getCreatedAt() != null ) {
            authorDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getCreatedAt() ) );
        }
        if ( authors.getUpdatedAt() != null ) {
            authorDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getUpdatedAt() ) );
        }
        if ( authors.getDeletedAt() != null ) {
            authorDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( authors.getDeletedAt() ) );
        }
        authorDto.setFirstname( authors.getFirstname() );
        authorDto.setLastname( authors.getLastname() );
        authorDto.setAge( authors.getAge() );
        authorDto.setBookId( authors.getBookId() );

        return authorDto;
    }

    @Override
    public AuthorDto toDtoByNotBook(Authors author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        if ( author.getCreatedAt() != null ) {
            authorDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( author.getCreatedAt() ) );
        }
        if ( author.getUpdatedAt() != null ) {
            authorDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( author.getUpdatedAt() ) );
        }
        if ( author.getDeletedAt() != null ) {
            authorDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( author.getDeletedAt() ) );
        }
        if ( author.getAuthorId() != null ) {
            authorDto.setAuthorId( String.valueOf( author.getAuthorId() ) );
        }
        authorDto.setFirstname( author.getFirstname() );
        authorDto.setLastname( author.getLastname() );
        authorDto.setAge( author.getAge() );

        return authorDto;
    }

    @Override
    public void validate(Authors author, AuthorDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getAuthorId() != null ) {
            author.setAuthorId( Integer.parseInt( dto.getAuthorId() ) );
        }
        if ( dto.getFirstname() != null ) {
            author.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            author.setLastname( dto.getLastname() );
        }
        if ( dto.getAge() != null ) {
            author.setAge( dto.getAge() );
        }
        if ( dto.getBookId() != null ) {
            author.setBookId( dto.getBookId() );
        }
        if ( dto.getCreatedAt() != null ) {
            author.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        if ( dto.getUpdatedAt() != null ) {
            author.setUpdatedAt( LocalDateTime.parse( dto.getUpdatedAt() ) );
        }
        if ( dto.getDeletedAt() != null ) {
            author.setDeletedAt( LocalDateTime.parse( dto.getDeletedAt() ) );
        }
    }
}
