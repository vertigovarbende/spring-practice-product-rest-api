package com.deveyk.northwind.auth.service.jwt;

import com.deveyk.northwind.auth.model.enums.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;


    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
            if (!isValid) {
                log.warn("Token validation failed for username: {}", username);
            }
        return isValid;
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());
            return false;
        }
    }
    /*
    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (isTokenExpired(token)) {
            log.warn("Expired JWT Token");
            throw new ExpiredJwtException(null, null, "Expired JWT Token");
        }
        final String username = extractUsername(token);
        if (!username.equals(userDetails.getUsername())) {
            log.warn("Token validation failed for username: {}", username);
            throw new JwtException("Token validation error");
        }
        return true;
    }
     */

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractType(String token) {
        return (String) extractClaim(token, (Claims claims) -> (claims.get("type")));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(UserDetails userDetails) {
        HashMap<String, Object> extraClaimsForToken = new HashMap<>();
        extraClaimsForToken.put("type", TokenType.ACCESS_TOKEN.name());
        return generateJWT(extraClaimsForToken, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        HashMap<String, Object> extraClaimsForToken = new HashMap<>();
        extraClaimsForToken.put("type", TokenType.REFRESH_TOKEN.name());
        return generateJWT(extraClaimsForToken, userDetails, refreshExpiration);
    }

    private String generateJWT(Map<String, Object> extraClaimsForToken, UserDetails userDetails, long expiration) {
        return buildToken(extraClaimsForToken, userDetails, expiration);
    }

    private String buildToken(Map<String, Object> extraClaimsForToken, UserDetails userDetails, long expiration) {
        var userAuthorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("authorities", userAuthorities)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claims(extraClaimsForToken)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret)))
                .compact();
    }

}