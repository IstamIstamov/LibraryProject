package com.company.LibraryProject.dto;

import com.company.LibraryProject.model.Authorities;
import com.company.LibraryProject.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails {

    private Integer userId;
    @NotBlank(message = "firstname cannot be null or empty")
    private String firstName;
    @NotBlank(message = "lastname cannot be null or empty")
    private String lastName;
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "password cannot be null or empty")
    @Size(min = 6, max = 10, message = "password  invalid")
    //@Pattern(regexp = "[a-zA-Z0-9]{6}", message = "Regular expression error.")
    //@Pattern(regexp = "[a-z]{1,3}[A-Z]{1,7}[0-9]{1,6}", message = "Regular expression error.")
    @Pattern(regexp = "[^abc]{3}[A-Z0-9]{6}", message = "Regular expression error.")
    private String password;

    @NotBlank(message = "phoneNumber cannot be null or empty")
    private String phoneNumber;
    private String username;
    private Boolean enabled;

    /*private Set<CardDto> cards;
    private Set<OrdersDto> orders;*/

    private Set<Authorities> authority;

    private Gender gender;

    private LocalDateTime birthdate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(authority)
                .map(auth -> auth.stream()
                        .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                        .toList())
                .orElse(new ArrayList<>());
    }


    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.enabled;
    }
}
