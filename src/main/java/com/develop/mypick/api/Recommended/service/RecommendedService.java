package com.develop.mypick.api.Recommended.service;

import com.develop.mypick.api.Recommended.dto.response.RecommendedResponse;
import com.develop.mypick.api.userPhysical.dto.response.UserPhysicalResponse;
import com.develop.mypick.api.userPhysical.service.UserPhysicalService;
import com.develop.mypick.common.config.ChatGptConfig;
import com.develop.mypick.domain.Recommended.repo.RecommendedRepository;
import com.develop.mypick.domain.user.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpHeaders;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendedService {


    private final ChatGptService chatGptService;




}
