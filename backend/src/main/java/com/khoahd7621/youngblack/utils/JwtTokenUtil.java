package com.khoahd7621.youngblack.utils;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.models.user.TokenPayload;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;
    @Value("${JWT_ACCESS_TOKEN_EXPIRED_DATE}")
    private long accessTokenExpiredDate;
    @Value("${JWT_REFRESH_TOKEN_EXPIRED_DATE}")
    private long refreshTokenExpiredDate;

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        TokenPayload tokenPayload = TokenPayload.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone()).build();
        claims.put("payload", tokenPayload);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("name", "ACCESS_TOKEN")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String generateRefreshToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        TokenPayload tokenPayload = TokenPayload.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone()).build();
        claims.put("payload", tokenPayload);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("name", "REFRESH_TOKEN")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}
