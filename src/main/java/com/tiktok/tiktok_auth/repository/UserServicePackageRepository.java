package com.tiktok.tiktok_auth.repository;
import com.tiktok.tiktok_auth.entity.User;
import com.tiktok.tiktok_auth.entity.UserServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserServicePackageRepository extends JpaRepository<UserServicePackage, Long> {
    boolean existsByUserAndActiveIsTrueAndEndAtAfter(User user, LocalDateTime time);
}
