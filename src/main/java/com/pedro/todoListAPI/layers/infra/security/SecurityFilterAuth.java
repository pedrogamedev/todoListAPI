package com.pedro.todoListAPI.layers.infra.security;

import com.pedro.todoListAPI.layers.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterAuth extends OncePerRequestFilter {

    @Autowired
    AuthTokenService authTokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = this.recoverToken(request);

        //checking if it`s not login/register related, if not, checks if token/user is null or blank, if it is, throws error
        if(!request.getServletPath().startsWith("/auth/login") && !request.getServletPath().startsWith("/auth/register"))
        {
            if (token == null) unauthorizedResponse(response,"invalid token bearer.");
            else{

                String login = authTokenService.validateToken(token);
                UserDetails user = userRepository.findByLogin(login);

                if (user == null) unauthorizedResponse(response,"inexistent or expired token.");

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