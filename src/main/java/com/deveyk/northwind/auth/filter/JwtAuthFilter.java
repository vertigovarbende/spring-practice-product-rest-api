package com.deveyk.northwind.auth.filter;

import com.deveyk.northwind.auth.model.enums.TokenType;
import com.deveyk.northwind.auth.service.jwt.CustomUserDetailsService;
import com.deveyk.northwind.auth.service.jwt.JwtService;
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

/**
 * <p>
 * The JwtAuthFilter class is responsible for processing JWT authentication
 * for incoming HTTP requests within the application's security filter chain.
 * It extracts and validates the JWT token from the "Authorization" header,
 * and sets the appropriate authentication in the Security Context if the token is valid.
 * <p>
 * This filter extends OncePerRequestFilter, ensuring its execution occurs
 * exactly once per request.
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;


    /**
     * Handles filtering of HTTP requests to implement JWT-based authentication.
     * This method validates the JWT token present in the request's "Authorization" header,
     * extracts the username, verifies token validity, and sets the authentication in the Spring Security context.
     *
     * @param request the HttpServletRequest object representing the client's request
     * @param response the HttpServletResponse object representing the response to the client
     * @param filterChain the FilterChain object for invoking the next filter in the chain
     * @throws ServletException if an exception related to servlet processing occurs
     * @throws IOException if an I/O error occurs during processing of the request or response
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = authorizationHeader.substring(7);
            final String username = this.jwtService.extractUsername(token);
            final String tokenType = this.jwtService.extractType(token);

            if (tokenType.equals(TokenType.ACCESS_TOKEN.name()) && username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    log.warn("Invalid JWT token for username: {}", username);
                }
            }
        } catch (Exception ex) {
            log.error("Error processing JWT Authentication: {}", ex.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

  /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null && !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(7);
        final String username = jwtService.extractUsername(token);
        final TokenType tokenType = this.jwtService.extractType(token);

        if (tokenType.equals(TokenType.ACCESS_TOKEN) && username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                log.warn("Invalid Token");
            }
        }
        filterChain.doFilter(request, response);

    }
*/
}
