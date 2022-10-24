package com.khoahd7621.youngblack.utils;

import com.khoahd7621.youngblack.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;
    @Value("${JWT_ACCESS_TOKEN_EXPIRED_DATE}")
    private long accessTokenExpiredDate;
    @Value("${JWT_REFRESH_TOKEN_EXPIRED_DATE}")
    private long refreshTokenExpiredDate;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("name", "ACCESS_TOKEN")
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("name", "REFRESH_TOKEN")
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(String token, User user) {
        long userId = getUserIdFromToken(token);
        String email = getUserEmailFromToken(token);
        return userId == user.getId() &&
                email.equals(user.getEmail()) &&
                !isTokenExpired(token);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public long getUserIdFromToken(String token) {
        return getClaimFromToken(token, (Claims claim) -> Long.parseLong(claim.get("userId").toString()));
    }

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, (Claims claim) -> claim.get("email").toString());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpirationDateFromToken(token);
        return expiredDate.before(new Date());
    }

}
