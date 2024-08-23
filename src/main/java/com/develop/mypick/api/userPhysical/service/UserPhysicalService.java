package com.develop.mypick.api.userPhysical.service;

import com.develop.mypick.api.user.dto.request.UserRequest;
import com.develop.mypick.common.exception.ErrorCode;
import com.develop.mypick.common.exception.ErrorException;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.userPhysical.entity.UserPhysical;
import com.develop.mypick.domain.userPhysical.repo.UserPhysicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserPhysicalService {
    private final UserPhysicalRepository userPhysicalRepository;

    @Transactional
    public void createUserPhysical(AuthUser user, UserRequest request){
        boolean isExist = userPhysicalRepository.existsByAuthUser(user);
        if (isExist){
            throw new ErrorException(ErrorCode.EXITS_USER_PHYSICAL);
        }
        UserPhysical userPhysical = UserPhysical.builder()
                .age(request.age())
                .authUser(user)
                .chronicDiseases(request.chronicDiseases())
                .gender(request.gender())
                .goal(request.goal())
                .height(request.height())
                .weight(request.weight())
                .goal(request.goal())
                .build();
        userPhysicalRepository.save(userPhysical);
    }

    public UserPhysical findUserPhysical(AuthUser user){

        return userPhysicalRepository.findByAuthUser(user)
                .orElseThrow(() -> new ErrorException(ErrorCode.NOT_FOUND_USER));

    }
}
