package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.OrdersBookDto;
import com.company.LibraryProject.model.OrdersBook;
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
public class OrdersBookMapperImpl extends OrdersBookMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public OrdersBook toEntity(OrdersBookDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrdersBook ordersBook = new OrdersBook();

        ordersBook.setOrdersBookId( dto.getOrdersBookId() );
        ordersBook.setOrdersId( dto.getOrdersId() );

        return ordersBook;
    }

    @Override
    public OrdersBookDto toDto(OrdersBook ordersBook) {
        if ( ordersBook == null ) {
            return null;
        }

        OrdersBookDto ordersBookDto = new OrdersBookDto();

        if ( ordersBook.getCreatedAt() != null ) {
            ordersBookDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getCreatedAt() ) );
        }
        if ( ordersBook.getUpdatedAt() != null ) {
            ordersBookDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getUpdatedAt() ) );
        }
        if ( ordersBook.getDeletedAt() != null ) {
            ordersBookDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getDeletedAt() ) );
        }
        ordersBookDto.setOrdersBookId( ordersBook.getOrdersBookId() );

        ordersBookDto.setBooksDto( ordersBook.getBooks().stream().map(bookMapper::toDtoByNotOrdersBookId).collect(Collectors.toSet()) );
        ordersBookDto.setOrdersDto( ordersMapper.toDtoByNotOrdersBook(ordersRepository.findByOrdersIdAndDeletedAtIsNull(ordersBook.getOrdersBookId()).get()) );

        return ordersBookDto;
    }

    @Override
    public OrdersBookDto toDtoByNotOrdersId(OrdersBook ordersBook) {
        if ( ordersBook == null ) {
            return null;
        }

        OrdersBookDto ordersBookDto = new OrdersBookDto();

        if ( ordersBook.getCreatedAt() != null ) {
            ordersBookDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getCreatedAt() ) );
        }
        if ( ordersBook.getUpdatedAt() != null ) {
            ordersBookDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getUpdatedAt() ) );
        }
        if ( ordersBook.getDeletedAt() != null ) {
            ordersBookDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( ordersBook.getDeletedAt() ) );
        }
        ordersBookDto.setOrdersBookId( ordersBook.getOrdersBookId() );

        return ordersBookDto;
    }

    @Override
    public void update(OrdersBook ordersBook, OrdersBookDto ordersBookDto) {
        if ( ordersBookDto == null ) {
            return;
        }

        if ( ordersBookDto.getOrdersBookId() != null ) {
            ordersBook.setOrdersBookId( ordersBookDto.getOrdersBookId() );
        }
        if ( ordersBookDto.getOrdersId() != null ) {
            ordersBook.setOrdersId( ordersBookDto.getOrdersId() );
        }
        if ( ordersBookDto.getCreatedAt() != null ) {
            ordersBook.setCreatedAt( LocalDateTime.parse( ordersBookDto.getCreatedAt() ) );
        }
        if ( ordersBookDto.getUpdatedAt() != null ) {
            ordersBook.setUpdatedAt( LocalDateTime.parse( ordersBookDto.getUpdatedAt() ) );
        }
        if ( ordersBookDto.getDeletedAt() != null ) {
            ordersBook.setDeletedAt( LocalDateTime.parse( ordersBookDto.getDeletedAt() ) );
        }
    }
}
