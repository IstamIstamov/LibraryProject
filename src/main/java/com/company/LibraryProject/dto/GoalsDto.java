package com.company.LibraryProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalsDto {
    private Integer goalsId;
    @NotBlank(message = "language cannot be null or empty.")
    private String language;
    @NotBlank(message = "description cannot be null or empty.")
    private String description;
    @NotNull(message = "bookId cannot be null.")
    private Integer bookId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
