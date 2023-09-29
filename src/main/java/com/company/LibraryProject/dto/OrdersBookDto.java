package com.company.LibraryProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersBookDto {
    private Integer ordersBookId;
    private Integer ordersId;
    private OrdersDto ordersDto;
    private Set<BooksDto> booksDto;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
