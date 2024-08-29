package com.develop.mypick.domain.user.repository;

import com.develop.mypick.domain.user.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository{

    private final RedisTemplate redisTemplate;


    public void save(RefreshToken refreshToken){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getEmail(),30,TimeUnit.DAYS);
        redisTemplate.expire(refreshToken.getRefreshToken(),30,TimeUnit.DAYS);
    }

    public boolean deleteRefreshTokenByKey(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }



}
