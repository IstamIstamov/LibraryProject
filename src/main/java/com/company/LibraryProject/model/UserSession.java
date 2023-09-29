package com.company.LibraryProject.model;

import com.company.LibraryProject.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(timeToLive = 60 * 3)
public class UserSession {

    @Id
    public String sessionId;
    private UserDto userDto;
}
