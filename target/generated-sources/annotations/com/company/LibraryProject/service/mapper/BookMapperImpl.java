package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.BooksDto;
import com.company.LibraryProject.model.Book;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T23:00:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class BookMapperImpl extends BookMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public Book toEntity(BooksDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        book.setBookId( dto.getBookId() );
        book.setName( dto.getName() );
        book.setPrice( dto.getPrice() );
        book.setPage( dto.getPage() );
        book.setAmount( dto.getAmount() );
        book.setOrdersBookId( dto.getOrdersBookId() );
        book.setPublisherAt( dto.getPublisherAt() );

        return book;
    }

    @Override
    public BooksDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BooksDto booksDto = new BooksDto();

        booksDto.setBookId( book.getBookId() );
        booksDto.setName( book.getName() );
        booksDto.setPrice( book.getPrice() );
        booksDto.setPage( book.getPage() );
        booksDto.setAmount( book.getAmount() );
        booksDto.setOrdersBookId( book.getOrdersBookId() );
        booksDto.setPublisherAt( book.getPublisherAt() );
        if ( book.getCreatedAt() != null ) {
            booksDto.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( book.getCreatedAt() ) );
        }
        if ( book.getUpdatedAt() != null ) {
            booksDto.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( book.getUpdatedAt() ) );
        }
        if ( book.getDeletedAt() != null ) {
            booksDto.setDeletedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( book.getDeletedAt() ) );
        }

        booksDto.setAuthorsDto( book.getAuthors().stream().map(authorsMapper::toDtoNotAuthorId).collect(Collectors.toSet()) );
        booksDto.setGoalsDto( goalsMapper.toDtoNotBookId(book.getGoals()) );
        booksDto.setPublisherDto( publisherMapper.toDtoNotBookId(book.getPublisher()) );

        return booksDto;
    }

    @Override
    public BooksDto toDtoByNotOrdersBookId(Book book) {
        if ( book == null ) {
            return null;
        }

        BooksDto booksDto = new BooksDto();

        if ( book.getCreatedAt() != null ) {
            booksDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( book.getCreatedAt() ) );
        }
        if ( book.getUpdatedAt() != null ) {
            booksDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( book.getUpdatedAt() ) );
        }
        if ( book.getDeletedAt() != null ) {
            booksDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( book.getDeletedAt() ) );
        }
        booksDto.setBookId( book.getBookId() );
        booksDto.setName( book.getName() );
        booksDto.setPrice( book.getPrice() );
        booksDto.setPage( book.getPage() );
        booksDto.setAmount( book.getAmount() );
        booksDto.setPublisherAt( book.getPublisherAt() );

        return booksDto;
    }

    @Override
    public void update(Book book, BooksDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBookId() != null ) {
            book.setBookId( dto.getBookId() );
        }
        if ( dto.getName() != null ) {
            book.setName( dto.getName() );
        }
        if ( dto.getPrice() != null ) {
            book.setPrice( dto.getPrice() );
        }
        if ( dto.getPage() != null ) {
            book.setPage( dto.getPage() );
        }
        if ( dto.getAmount() != null ) {
            book.setAmount( dto.getAmount() );
        }
        if ( dto.getOrdersBookId() != null ) {
            book.setOrdersBookId( dto.getOrdersBookId() );
        }
        if ( dto.getPublisherAt() != null ) {
            book.setPublisherAt( dto.getPublisherAt() );
        }
        if ( dto.getCreatedAt() != null ) {
            book.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        if ( dto.getUpdatedAt() != null ) {
            book.setUpdatedAt( LocalDateTime.parse( dto.getUpdatedAt() ) );
        }
        if ( dto.getDeletedAt() != null ) {
            book.setDeletedAt( LocalDateTime.parse( dto.getDeletedAt() ) );
        }
    }
}
