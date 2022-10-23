package com.khoahd7621.youngblack.utils;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.models.TokenPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

    public TokenPayload getTokenPayload(String token) {
        final Claims claims = parseToken(token).getBody();
        Map<String, Object> mapResult = (Map<String, Object>) claims.get("payload");
        return TokenPayload.builder()
                .id(Long.parseLong(mapResult.get("id").toString()))
                .email(mapResult.get("email").toString())
                .phone(mapResult.get("phone").toString()).build();

    }

    public boolean validateToken(String token, User user) {
        TokenPayload tokenPayload = getTokenPayload(token);
        return tokenPayload.getId() == user.getId() &&
                tokenPayload.getEmail().equals(user.getEmail()) &&
                tokenPayload.getPhone().equals(user.getPhone()) &&
                !isTokenExpired(token);
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }

    private boolean isTokenExpired(String token) {
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        Date expiredDate = claims.getExpiration();
        return expiredDate.before(new Date());
    }


//    public TokenPayload getTokenPayload(String token) {
//        return getClaimsFromToken(token, (Claims claim) -> {
//            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
//            return TokenPayload.builder()
//                    .id((Long) mapResult.get("id"))
//                    .email((String) mapResult.get("email"))
//                    .phone((String) mapResult.get("phone")).build();
//        });
//    }

//    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claimsResolver.apply(claims);
//    }
}
