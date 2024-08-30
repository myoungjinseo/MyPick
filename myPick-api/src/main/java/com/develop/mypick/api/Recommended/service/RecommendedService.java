package com.develop.mypick.api.Recommended.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendedService {


    private final ChatGptService chatGptService;




}
