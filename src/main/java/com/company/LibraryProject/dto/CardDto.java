package com.company.LibraryProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private Integer cardId;
    @NotBlank(message = "cardName cannot be null or empty.")
    private String cardName;
   // @Pattern(regexp = "[0-9]{16}", message = "Regular expression error.")
    @NotNull(message = "cardNumber cannot be null.")
    private Long cardNumber;
    @NotNull(message = "userId cannot be null.")
    private Integer userId;
    private UserDto userDto;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}



