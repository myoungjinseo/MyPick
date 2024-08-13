package com.develop.mypick.common.jwt;

import com.develop.mypick.api.user.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import io.jsonwebtoken.Jwts;
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

    public String createToken(String username,Long time) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("username",username);

        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + time);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUserUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String authToken) {
        UserDetails userDetails = userDetailService.loadUserByUsername(getUserUsername(authToken));
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }


}
