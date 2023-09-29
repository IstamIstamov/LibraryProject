package com.company.LibraryProject.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
    private Integer id;
    private String username;
    private String authority;
    private Integer userId;
}
