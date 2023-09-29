package com.company.LibraryProject.security;

import com.company.LibraryProject.dto.UserDto;
import com.company.LibraryProject.repository.UserSessionRepository;
import com.company.LibraryProject.service.UserService;
import com.google.gson.Gson;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final UserSessionRepository userSessionRepository;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authentication = request.getHeader("Authorization");

        if (!StringUtils.isBlank(authentication) && authentication.startsWith("Bearer ")) {
            String token = authentication.substring(7);
            if (jwtUtil.isValid(token)) {
                String sub = jwtUtil.getClaims("sub", token, String.class);
                this.userSessionRepository.findById(sub)
                        .ifPresent(userSession -> {
                                    SecurityContextHolder.getContext().setAuthentication(
                                            new UsernamePasswordAuthenticationToken(
                                                    userSession.getUserDto(),
                                                    userSession.getUserDto().getPassword(),
                                                    userSession.getUserDto().getAuthorities()
                                            )
                                    );
                                }
                        );
            }
        }
        filterChain.doFilter(request, response);
    }

}
