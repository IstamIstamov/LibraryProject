package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.OrdersDto;
import com.company.LibraryProject.model.Orders;
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
public class OrdersMapperImpl extends OrdersMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public Orders toEntity(OrdersDto ordersDto) {
        if ( ordersDto == null ) {
            return null;
        }

        Orders orders = new Orders();

        orders.setOrdersId( ordersDto.getOrdersId() );
        orders.setUserId( ordersDto.getUserId() );
        orders.setTotal( ordersDto.getTotal() );

        return orders;
    }

    @Override
    public OrdersDto toDto(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrdersDto ordersDto = new OrdersDto();

        if ( orders.getCreatedAt() != null ) {
            ordersDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getCreatedAt() ) );
        }
        if ( orders.getUpdatedAt() != null ) {
            ordersDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getUpdatedAt() ) );
        }
        if ( orders.getDeletedAt() != null ) {
            ordersDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getDeletedAt() ) );
        }
        ordersDto.setOrdersId( orders.getOrdersId() );
        ordersDto.setTotal( orders.getTotal() );

        ordersDto.setUserDto( userMapper.toDtoByNotCards(userRepository.findByUserIdAndDeletedAtIsNull(orders.getUserId()).get()) );
        ordersDto.setOrdersBooksDto( orders.getOrdersBooks().stream().map(ordersBookMapper::toDtoByNotOrdersId).collect(Collectors.toSet()) );

        return ordersDto;
    }

    @Override
    public OrdersDto toDtoByNotOrdersBook(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrdersDto ordersDto = new OrdersDto();

        if ( orders.getCreatedAt() != null ) {
            ordersDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getCreatedAt() ) );
        }
        if ( orders.getUpdatedAt() != null ) {
            ordersDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getUpdatedAt() ) );
        }
        if ( orders.getDeletedAt() != null ) {
            ordersDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getDeletedAt() ) );
        }
        ordersDto.setOrdersId( orders.getOrdersId() );
        ordersDto.setUserId( orders.getUserId() );
        ordersDto.setTotal( orders.getTotal() );

        return ordersDto;
    }

    @Override
    public OrdersDto toDtoNotUserId(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrdersDto ordersDto = new OrdersDto();

        if ( orders.getCreatedAt() != null ) {
            ordersDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getCreatedAt() ) );
        }
        if ( orders.getUpdatedAt() != null ) {
            ordersDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getUpdatedAt() ) );
        }
        if ( orders.getDeletedAt() != null ) {
            ordersDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( orders.getDeletedAt() ) );
        }
        ordersDto.setOrdersId( orders.getOrdersId() );
        ordersDto.setTotal( orders.getTotal() );

        return ordersDto;
    }

    @Override
    public void update(Orders orders, OrdersDto ordersDto) {
        if ( ordersDto == null ) {
            return;
        }

        if ( ordersDto.getOrdersId() != null ) {
            orders.setOrdersId( ordersDto.getOrdersId() );
        }
        if ( ordersDto.getUserId() != null ) {
            orders.setUserId( ordersDto.getUserId() );
        }
        if ( ordersDto.getTotal() != null ) {
            orders.setTotal( ordersDto.getTotal() );
        }
        if ( ordersDto.getCreatedAt() != null ) {
            orders.setCreatedAt( LocalDateTime.parse( ordersDto.getCreatedAt() ) );
        }
        if ( ordersDto.getUpdatedAt() != null ) {
            orders.setUpdatedAt( LocalDateTime.parse( ordersDto.getUpdatedAt() ) );
        }
        if ( ordersDto.getDeletedAt() != null ) {
            orders.setDeletedAt( LocalDateTime.parse( ordersDto.getDeletedAt() ) );
        }
    }
}
