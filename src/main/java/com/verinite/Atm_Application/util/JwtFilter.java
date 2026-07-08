package com.verinite.Atm_Application.util;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        String requestURI = request.getRequestURI();

        log.info("Incoming Request : {}", requestURI);


        String authHeader = request.getHeader("Authorization");

        log.info("Authorization Header : {}", authHeader);


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            log.warn("JWT Token not found");

            filterChain.doFilter(request, response);
            return;
        }


        String token = authHeader.substring(7);

        log.info("JWT Token Received");


        try {

            String username = jwtUtil.extractUsername(token);

            log.info("Username from JWT : {}", username);


            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {


                UserDetails userDetails =
                        customUserDetailsService.loadUserByUsername(username);


                log.info("User Loaded : {}", userDetails.getUsername());

                log.info("User Authorities : {}", userDetails.getAuthorities());


                if (jwtUtil.validateToken(token, userDetails)) {


                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );


                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );


                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);

                    System.out.println("Authenticated User: " + authentication.getName());
                    System.out.println("Authorities: " + authentication.getAuthorities());
                    log.info("Authentication Set Successfully");

                } else {

                    log.error("JWT Validation Failed");

                }

            }

        } catch (JwtException e) {

            log.error("JWT Exception : {}", e.getMessage());

        } catch (Exception e) {

            log.error("Authentication Error : {}", e.getMessage());

        }


        filterChain.doFilter(request, response);
    }
}