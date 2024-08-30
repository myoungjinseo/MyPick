package com.develop.mypick.common.jwt;

import com.develop.mypick.api.user.service.CustomUserDetailService;
import com.develop.mypick.common.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {
    private final String secret;
    private SecretKey secretKey;

    private final CustomUserDetailService userDetailService;


    public TokenProvider(@Value("${spring.jwt.secret}") String secret, CustomUserDetailService userDetailService) {
        this.secret = secret;
        this.userDetailService = userDetailService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email,Long time) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("email",email);

        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + time);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUserEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String authToken) {
        UserDetails userDetails = userDetailService.loadUserByUsername(getUserEmail(authToken));
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }
    public Jws<Claims> getAccessTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                .build()
                .parseClaimsJws(token);
    }

    public Jws<Claims> getRefreshTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                .build()
                .parseClaimsJws(token);
    }
    public boolean validateAccessToken(String token) {
        try {
            Jws<Claims> claims = getAccessTokenClaims(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Access token 만료: " + token);
            throw new JwtException(ErrorCode.EXPIRED_ACCESS_TOKEN.getMessage());
        } catch (SignatureException e) {
            log.info("SignatureException", e);
            throw new JwtException(ErrorCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException", e);
            throw new JwtException(ErrorCode.UNSUPPORTED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException", e);
            throw new JwtException(ErrorCode.UNKNOWN_ERROR.getMessage());
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = getRefreshTokenClaims(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Refresh token 만료: " + token);
            throw new JwtException(ErrorCode.EXPIRED_REFRESH_TOKEN.getMessage());
        } catch (SignatureException e) {
            log.info("SignatureException", e);
            throw new JwtException(ErrorCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException", e);
            throw new JwtException(ErrorCode.UNSUPPORTED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException", e);
            throw new JwtException(ErrorCode.UNKNOWN_ERROR.getMessage());
        }
    }




}
