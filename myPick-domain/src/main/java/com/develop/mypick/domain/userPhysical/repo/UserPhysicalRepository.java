package com.develop.mypick.domain.userPhysical.repo;

import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.userPhysical.entity.UserPhysical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhysicalRepository extends JpaRepository<UserPhysical, Long> {
    boolean existsByAuthUser(AuthUser user);


    Optional<UserPhysical> findByAuthUser(AuthUser user);
}
