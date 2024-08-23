package com.develop.mypick.domain.Recommended.repo;

import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.user.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendedRepository extends JpaRepository<Recommended, Long> {
    Boolean existsByAuthUser(AuthUser user);
    Optional<Recommended> findByAuthUser(AuthUser user);
}
