package com.develop.mypick.api.user.service;

import com.develop.mypick.api.user.dto.request.LoginRequest;
import com.develop.mypick.api.user.dto.response.TokenResponse;
import com.develop.mypick.api.user.dto.request.UserRequest;
import com.develop.mypick.api.user.dto.response.AccountResponse;
import com.develop.mypick.api.userPhysical.service.UserPhysicalService;
import com.develop.mypick.common.exception.ErrorCode;
import com.develop.mypick.common.exception.ErrorException;
import com.develop.mypick.common.jwt.TokenProvider;
import com.develop.mypick.domain.user.entity.RefreshToken;
import com.develop.mypick.domain.user.entity.Role;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.user.repository.RefreshTokenRepository;
import com.develop.mypick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserPhysicalService userPhysicalService;

    @Value("${spring.jwt.token-milliseconds.atk}")
    private Long atkTime;
    @Value("${spring.jwt.token-milliseconds.rtk}")
    private Long rtkTime;

    @Transactional
    public AccountResponse signUp(UserRequest userRequest) {
        boolean isExist = userRepository.existsByEmail(userRequest.email());
        if (isExist) {
            throw new ErrorException(ErrorCode.EXITS_EMAIL);
        }
        AuthUser authUser = AuthUser.builder()
                .email(userRequest.email())
                .username(userRequest.username())
                .password(passwordEncoder.encode(userRequest.password()))
                .roles(Collections.singleton(Role.ROLE_USER))
                .build();


        userRepository.save(authUser);

        userPhysicalService.createUserPhysical(authUser,userRequest);
        return AccountResponse.of(authUser);
    }

    @Transactional
    public TokenResponse signIn(LoginRequest userRequest) {
        AuthUser authUser = userRepository.findByEmail(userRequest.email())
                .orElseThrow(() -> new ErrorException(ErrorCode.LOGIN_FAIL));

        if (!passwordEncoder.matches(userRequest.password(), authUser.getPassword())) {
            throw new ErrorException(ErrorCode.LOGIN_FAIL);
        }

        String atk = tokenProvider.createToken(authUser.getEmail(), atkTime);
        String rtk = tokenProvider.createToken(authUser.getEmail(), rtkTime);

        RefreshToken refreshToken = new RefreshToken(rtk, authUser.getEmail(), LocalDateTime.now().plusSeconds(rtkTime / 1000));
        refreshTokenRepository.save(refreshToken);
        return new TokenResponse(atk, rtk);

    }
}