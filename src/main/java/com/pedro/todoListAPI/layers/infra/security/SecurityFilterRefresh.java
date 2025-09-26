package com.pedro.todoListAPI.layers.infra.security;

import com.auth0.jwt.JWT;
import com.pedro.todoListAPI.layers.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilterRefresh extends OncePerRequestFilter {

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().startsWith("/auth/refresh"))
        {
            String token = this.recoverToken(request);

            if (token == null) unauthorizedResponse(response,"invalid token bearer.");
            else if(JWT.decode(token).getClaim("token_type").asString().equals("auth")){
                unauthorizedResponse(response, "invalid token type");
            }
            else{
                String login = refreshTokenService.validateToken(token, JWT.decode(token).getSubject());
                UserDetails user = userRepository.findByLogin(login);

                if (user == null) unauthorizedResponse(response,"inexistent or expired refresh token.");

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    public void unauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"" + message + "\"}");
        response.flushBuffer();

    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.equals("Bearer")) return null;
        return authHeader.replace("Bearer ", "");
    }
}
