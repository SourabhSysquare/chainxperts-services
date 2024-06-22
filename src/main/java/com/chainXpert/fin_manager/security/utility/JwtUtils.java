package com.chainXpert.fin_manager.security.utility;

import com.chainXpert.fin_manager.enitity.User;
import com.chainXpert.fin_manager.security.configuration.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import java.util.function.Function;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */

@Component
@EnableConfigurationProperties(JwtConfiguration.class)
@AllArgsConstructor
public class JwtUtils {

    private final JwtConfiguration jwtConfiguration;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return ((Integer) extractAllClaims(token).get("user_id")).longValue();
    }

    public Integer extractTotalBalanceId(String token) {
        return (Integer) extractAllClaims(token).get("total_balance_id");
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtConfiguration.getJwt().getSecretKey()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User user, Long totalBalanceId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("account_creation_timestamp", user.getCreatedAt().toString());
        claims.put("user_id", user.getId());
        claims.put("total_balance_id", totalBalanceId);
        claims.put("name", user.getFirstName() + " " + user.getLastName());
        claims.put("scope", "user");
        return createToken(claims, user.getEmailId());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getJwt().getSecretKey()).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}