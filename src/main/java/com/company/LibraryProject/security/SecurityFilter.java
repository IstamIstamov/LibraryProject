package com.company.LibraryProject.security;


import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.repository.UserRepository;
import com.company.LibraryProject.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Your Logic
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isBlank(authorization) && authorization.startsWith("Basic ")) {

            String usernameAndPasswordBase64 = authorization.substring(6);

            String usernameAndPassword = new String(Base64.getDecoder().decode(usernameAndPasswordBase64));

            String username = usernameAndPassword.split(":")[0];

            String password = usernameAndPassword.split(":")[1];

            UserDto dto = userService.loadUserByUsername(username);

            //String passEncode = passwordEncoder.encode(password);
           /* if (passEncode.equals(dto.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                dto.getUsername(),
                                dto.getPassword(),
                                dto.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }*/

            if (passwordEncoder.matches(password, dto.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                dto,
                                dto.getPassword(),
                                dto.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
