package com.company.LibraryProject.model;

import com.company.LibraryProject.dto.UserDto;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 60 * 60 * 24)
public class RefreshToken {
    @Id
    private String refreshTokenId;
    private UserDto userDto;
}
