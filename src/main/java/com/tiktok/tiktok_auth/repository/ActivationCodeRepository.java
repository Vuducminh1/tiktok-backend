package com.tiktok.tiktok_auth.repository;

import com.tiktok.tiktok_auth.entity.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByCodeAndUsedIsFalseAndExpiredAtAfter(String code, LocalDateTime time);
}
