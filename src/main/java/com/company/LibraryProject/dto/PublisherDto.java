package com.company.LibraryProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherDto {
    private Integer publisherId;
    @NotBlank(message = "publisher cannot be null or empty")
    private String publisher;
    @NotBlank(message = "Location cannot be null or empty")
    private String location;
    @NotNull(message = "bookId cannot be null.")
    private Integer bookId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
