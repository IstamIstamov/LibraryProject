package com.company.LibraryProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    private String authorId;
    @NotBlank(message = "firstName cannot be null or empty")
    private String firstname;
    @NotBlank(message = "lastname cannot be null or empty")
    private String lastname;
    @NotNull(message = "age cannot be null")
    private Integer age;
    private Integer bookId;
    private BooksDto booksDto;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

}
