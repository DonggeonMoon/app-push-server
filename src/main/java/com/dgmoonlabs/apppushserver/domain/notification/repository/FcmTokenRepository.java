package com.dgmoonlabs.apppushserver.domain.notification.repository;

import com.dgmoonlabs.apppushserver.domain.notification.model.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
}
