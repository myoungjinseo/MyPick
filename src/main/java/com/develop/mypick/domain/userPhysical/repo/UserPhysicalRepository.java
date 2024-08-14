package com.develop.mypick.domain.userPhysical.repo;

import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.userPhysical.entity.UserPhysical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhysicalRepository extends JpaRepository<UserPhysical, Long> {
    boolean existsByAuthUser(AuthUser user);
}
