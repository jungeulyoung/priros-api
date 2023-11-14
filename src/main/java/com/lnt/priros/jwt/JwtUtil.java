package com.lnt.priros.jwt;

import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.dto.TokenClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 30; // 30분
    private final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 30; // 30일


    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(AuthenticUser authenticUser) {
        Claims claims = Jwts.claims();
        claims.put("userId", authenticUser.getUsername());
        claims.put("firmCode", authenticUser.getFirmCode());
        claims.put("role", authenticUser.getRole());
        claims.put("part", authenticUser.getPart());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDATION_SECOND))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256).compact();
    }

    public String createRefreshToken(AuthenticUser authenticUser) {
        Claims claims = Jwts.claims();
        claims.put("userId", authenticUser.getUsername());
        claims.put("firmCode", authenticUser.getFirmCode());
        claims.put("role", authenticUser.getRole());
        claims.put("part", authenticUser.getPart());
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDATION_SECOND))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256).compact();
    }

    public TokenClaims getTokenClaims(String token) {
        TokenClaims tokenClaims = null;
        try {
            Claims claims = extractAllClaims(token);
            tokenClaims = new TokenClaims(
                    claims.get("userId", String.class),
                    claims.get("firmCode", String.class),
                    claims.get("part", String.class),
                    claims.get("role", String.class)
            );

        } catch (Exception e) {
            log.error("getTokenClaims :: {}", e.getMessage());
        }
        return tokenClaims;
    }

    public String getUserId(String token) {
        return extractAllClaims(token).get("userId", String.class);
    }

    public String getFirmCode(String token) {
        return extractAllClaims(token).get("firmCode", String.class);
    }

    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token).getBody();
    }

    public String extractBearerFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }

}
