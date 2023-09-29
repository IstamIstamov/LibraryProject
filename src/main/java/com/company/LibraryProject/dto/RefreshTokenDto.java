package com.company.LibraryProject.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefreshTokenDto implements Serializable {
    private String refreshToken;
}
